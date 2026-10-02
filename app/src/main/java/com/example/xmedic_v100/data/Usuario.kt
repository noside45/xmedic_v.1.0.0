package com.example.xmedic_v100.data

/**
 * Entidad que representa a un usuario en la Base de Datos.
 */
data class Usuario(
    val email: String,
    val contrasena: String,
    val nombre: String = "",
    val rol: String = "PACIENTE"
)
