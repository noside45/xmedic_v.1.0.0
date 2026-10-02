package com.example.xmedic_v100.data

/**
 * Data Access Object (DAO) para la gestión de usuarios.
 * Mantiene las operaciones principales simulando consultas SQL (Room).
 */
class UsuarioDao {
    // Almacenamiento en memoria para demostración sin Room (por simplicidad de compilación).
    // En Room, aquí irían los @Insert, @Query("SELECT * FROM usuario..."), etc.
    private val usuarios = mutableListOf<Usuario>()

    init {
        // Usuario por defecto para pruebas rápidas
        usuarios.add(Usuario("admin@xmedic.com", "admin123", "Administrador"))
    }

    fun insertUsuario(usuario: Usuario): Boolean {
        if (usuarios.any { it.email == usuario.email }) return false // Ya existe
        usuarios.add(usuario)
        return true
    }

    fun getUsuarioByEmail(email: String): Usuario? {
        return usuarios.find { it.email == email }
    }

    fun updatePassword(email: String, nuevaContrasena: String): Boolean {
        val index = usuarios.indexOfFirst { it.email == email }
        if (index != -1) {
            val user = usuarios[index]
            usuarios[index] = user.copy(contrasena = nuevaContrasena)
            return true
        }
        return false
    }
}
