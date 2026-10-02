package com.example.xmedic_v100.data

/**
 * Gestor principal de la base de datos (Instancia Singleton).
 * Sigue la estructura de una RoomDatabase.
 */
object BaseDeDatos {
    // Instancia única del DAO
    private val _usuarioDao = UsuarioDao()

    fun usuarioDao(): UsuarioDao {
        return _usuarioDao
    }
}
