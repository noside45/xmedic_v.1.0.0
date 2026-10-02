package com.example.xmedic_v100

class PersonaDao {
    private val personas = mutableListOf<persona>()

    init {
        personas.add(persona(id = 1, nombre = "Administrador", email = "admin@xmedic.com", contrasena = "admin123"))
    }

    fun insertPersona(nuevaPersona: persona): Long {
        if (personas.any { it.email == nuevaPersona.email }) return -1
        val newId = (personas.maxOfOrNull { it.id } ?: 0) + 1
        personas.add(nuevaPersona.copy(id = newId))
        return newId.toLong()
    }

    fun getPersonaByEmail(email: String): persona? {
        return personas.find { it.email == email }
    }

    fun updatePassword(email: String, nuevaContrasena: String) {
        val index = personas.indexOfFirst { it.email == email }
        if (index != -1) {
            val user = personas[index]
            personas[index] = user.copy(contrasena = nuevaContrasena)
        }
    }
}

class UserConfigDao {
    private val configs = mutableListOf<UserConfig>()

    fun insertConfig(config: UserConfig) {
        val index = configs.indexOfFirst { it.uid == config.uid }
        if (index != -1) {
            configs[index] = config
        } else {
            configs.add(config)
        }
    }

    fun getConfig(uid: String): UserConfig? {
        return configs.find { it.uid == uid }
    }

    fun updateTokens(uid: String, tokens: Int) {
        val index = configs.indexOfFirst { it.uid == uid }
        if (index != -1) {
            configs[index] = configs[index].copy(local_tokens = tokens)
        }
    }
}

object BaseDeDatos {
    val personaDao = PersonaDao()
    val userConfigDao = UserConfigDao()
}
