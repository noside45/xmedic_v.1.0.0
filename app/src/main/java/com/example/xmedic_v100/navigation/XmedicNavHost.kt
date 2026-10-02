package com.example.xmedic_v100.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xmedic_v100.ui.screens.LoginScreen
import com.example.xmedic_v100.ui.screens.CreateAccountScreen
import com.example.xmedic_v100.ui.screens.ForgotPasswordScreen
import com.example.xmedic_v100.ui.screens.RoleSelectionScreen
import com.example.xmedic_v100.ui.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
    object ForgotPassword : Screen("forgot_password")
    object RoleSelection : Screen("role_selection")
}

@Composable
fun XmedicNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onLoginSuccess = {
                    navController.navigate(Screen.RoleSelection.route)
                },
                onGoogleSignIn = {
                    // Integración Google Credential Manager
                    navController.navigate(Screen.RoleSelection.route)
                },
                onForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
                onCreateAccount = {
                    navController.navigate(Screen.CreateAccount.route)
                }
            )
        }

        composable(Screen.CreateAccount.route) {
            CreateAccountScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = { 
                    navController.navigate(Screen.RoleSelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    } 
                }
            )
        }

        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateBack = { navController.popBackStack() },
                onSuccess = { 
                    navController.popBackStack() // Vuelve a login
                }
            )
        }

        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onContinue = { selectedRole ->
                    // Guardar rol en DataStore / ViewModel y avanzar a pantalla principal
                },
                onSkip = {
                    // Omitir selección y continuar
                }
            )
        }
    }
}
