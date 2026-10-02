package com.example.xmedic_v100

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class AuthState {
    object Idle : AuthState()
    data class Error(val message: String) : AuthState()
    data class Success(val message: String) : AuthState()
}

class PersonaViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = BaseDeDatos.personaDao
    private val configDao = BaseDeDatos.userConfigDao

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun resetState() {
        _authState.value = AuthState.Idle
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }

    // Simulando la comprobación de sesión de FirebaseAuth (Actividad 2)
    fun checkSession() {
        viewModelScope.launch {
            // FirebaseAuth.getInstance().currentUser != null
            // Aquí simularemos que no hay sesión activa retornando Error para ir a Login
            // Si retornara Success, iría a MainActivity (Dashboard)
            _authState.value = AuthState.Error("Sin sesión") 
        }
    }

    fun login(email: String, contrasena: String) {
        if (email.isBlank() || contrasena.isBlank()) {
            _authState.value = AuthState.Error("Por favor completa todos los campos.")
            return
        }
        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Formato de correo inválido.")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val user = dao.getPersonaByEmail(email)
            withContext(Dispatchers.Main) {
                if (user == null) {
                    _authState.value = AuthState.Error("Usuario no encontrado.")
                } else if (user.contrasena != contrasena) {
                    _authState.value = AuthState.Error("Contraseña incorrecta.")
                } else {
                    _authState.value = AuthState.Success("Inicio de sesión exitoso.")
                }
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch(Dispatchers.IO) {
            val existing = dao.getPersonaByEmail("google_user@gmail.com")
            if (existing == null) {
                dao.insertPersona(persona(nombre = "Usuario Google", email = "google_user@gmail.com", contrasena = "google123"))
            }
            withContext(Dispatchers.Main) {
                _authState.value = AuthState.Success("Inicio con Google exitoso.")
            }
        }
    }

    fun registrar(nombre: String, email: String, contrasena: String, confirmar: String) {
        if (nombre.isBlank() || email.isBlank() || contrasena.isBlank() || confirmar.isBlank()) {
            _authState.value = AuthState.Error("Por favor completa todos los campos.")
            return
        }
        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Formato de correo inválido.")
            return
        }
        if (contrasena != confirmar) {
            _authState.value = AuthState.Error("Las contraseñas no coinciden.")
            return
        }
        if (contrasena.length < 6) {
            _authState.value = AuthState.Error("La contraseña debe tener al menos 6 caracteres.")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = dao.getPersonaByEmail(email)
            withContext(Dispatchers.Main) {
                if (existingUser != null) {
                    _authState.value = AuthState.Error("El correo ya está registrado.")
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        val newPersona = persona(nombre = nombre, email = email, contrasena = contrasena)
                        dao.insertPersona(newPersona)
                        // Configurar tokens locales según Actividad 7
                        configDao.insertConfig(UserConfig(uid = email, local_tokens = 3, sync_status = "pending"))
                        withContext(Dispatchers.Main) {
                            _authState.value = AuthState.Success("Cuenta creada exitosamente.")
                        }
                    }
                }
            }
        }
    }

    fun recuperarContrasena(email: String, nuevaContrasena: String, confirmar: String) {
        if (email.isBlank() || nuevaContrasena.isBlank() || confirmar.isBlank()) {
            _authState.value = AuthState.Error("Por favor completa todos los campos.")
            return
        }
        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Formato de correo inválido.")
            return
        }
        if (nuevaContrasena != confirmar) {
            _authState.value = AuthState.Error("Las contraseñas no coinciden.")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val user = dao.getPersonaByEmail(email)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    viewModelScope.launch(Dispatchers.IO) {
                        dao.updatePassword(email, nuevaContrasena)
                        withContext(Dispatchers.Main) {
                            _authState.value = AuthState.Success("Contraseña actualizada exitosamente.")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error("No se encontró una cuenta con este correo.")
                }
            }
        }
    }
}
