# Xmedic - Salud y Tecnología en un Solo Lugar 🩺📱

<p align="center">
  <img src="app/src/main/res/drawable/mi_logo_xmedic_1.png" alt="Xmedic Logo" width="200"/>
</p>

Aplicación móvil desarrollada en **Android** utilizando **Jetpack Compose** y **Material 3**, diseñada para conectar a los usuarios con servicios de salud personalizados, gestión de perfiles y herramientas médicas avanzadas.

---

## 🚀 Características Principales

- **SplashScreen Animado**: Pantalla de bienvenida con animación fluida de carga (3 puntos pulsantes) y verificación automática de sesión.
- **Autenticación Completa (Email/Contraseña)**:
  - Formulario de inicio de sesión con validación de campos.
  - Creación de cuenta con validación de formato de correo (Regex), contraseña segura (mínimo 6 caracteres) y aceptación obligatoria de términos y condiciones.
  - Recuperación de contraseña integrada.
- **Inicio de Sesión Social con Google (SSO)**:
  - Integración moderna utilizando la API de **Android Credential Manager**.
  - Botón oficial con logotipo de Google (`logo_google.webp`).
- **Selector de Perfil / Roles Interactivo (Paso 2 de 2)**:
  - Grilla 2x2 con selección exclusiva (Paciente, Estudiante, Profesional de la salud, Empresa).
  - El botón de continuar permanece bloqueado hasta que el usuario elija un perfil válido.
- **Arquitectura Híbrida y Local (Room/Mock DB)**:
  - Estructura organizada bajo el patrón **MVVM** (Model-View-ViewModel).
  - Gestión de base de datos local y configuración de tokens iniciales offline (`UserConfig` con 3 tokens gratuitos).

---

## 🛠️ Tecnologías y Librerías Utilizadas

- **Kotlin** & **Jetpack Compose**: Interfaz de usuario declarativa y moderna.
- **Material 3 (M3)**: Sistema de diseño corporativo y paleta médica personalizada (`PrimaryTeal`, `DarkNavy`, `BackgroundPaleMint`).
- **Jetpack Navigation Compose**: Enrutamiento robusto entre pantallas (`Splash` -> `Login` -> `CreateAccount` -> `ForgotPassword` -> `RoleSelection`).
- **Android Credential Manager**: Autenticación segura de Google.
- **ViewModel & StateFlow**: Gestión reactiva de estados en la interfaz.

---

## 📂 Estructura del Proyecto

```text
com.example.xmedic_v100/
├── data/                  # Capa de datos y modelos (BaseDeDatos, PersonaDao, persona, UserConfig)
├── navigation/            # Configuración de rutas (XmedicNavHost)
├── ui/
│   ├── components/        # Elementos reusables (Botones, Inputs, Banners, Logotipos)
│   ├── screens/           # Pantallas principales (SplashScreen, LoginScreen, CreateAccountScreen, ForgotPasswordScreen, RoleSelectionScreen)
│   └── theme/             # Definición de colores, formas y temas Material 3
└── MainActivity.kt        # Actividad principal con soporte Edge-to-Edge
```

---

## ⚙️ Configuración y Ejecución

1. Clona este repositorio en tu equipo.
2. Abre el proyecto en **Android Studio** (Koala, Ladybug o versiones posteriores recomendadas).
3. Sincroniza las dependencias de Gradle (`Sync Project with Gradle Files`).
4. Configura tu **Web Client ID** de Google Cloud Console en el archivo `strings.xml` (`default_web_client_id`) si deseas probar el SSO real con Google.
5. Ejecuta la aplicación en un emulador o dispositivo físico con Android 8.0 (API 24) o superior.

---

## 📄 Licencia
Este proyecto es desarrollado bajo los estándares de arquitectura Android corporativa para Xmedic.
