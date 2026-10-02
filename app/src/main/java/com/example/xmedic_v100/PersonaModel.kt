package com.example.xmedic_v100

data class persona(
    val id: Int = 0,
    val nombre: String,
    val email: String,
    val contrasena: String,
    val rol: String = "PACIENTE"
)

data class UserConfig(
    val uid: String,
    val local_tokens: Int = 3,
    val sync_status: String = "pending"
)
