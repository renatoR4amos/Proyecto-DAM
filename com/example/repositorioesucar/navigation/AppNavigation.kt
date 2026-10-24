package com.example.repositorioesucar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.repositorioesucar.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Auth
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onLoginSuccess = { rol ->
                    if (rol == "DOCENTE") {
                        navController.navigate(Screen.DocenteHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = { navController.popBackStack() }
            )
        }

        // Estudiante
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToMateriales = { id -> navController.navigate(Screen.Materiales.createRoute(id)) },
                onNavigateToProgreso = { navController.navigate(Screen.Progreso.route) },
                onNavigateToConsulta = { navController.navigate(Screen.Consulta.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(
            route = Screen.Materiales.route,
            arguments = listOf(navArgument("asignaturaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val asigId = backStackEntry.arguments?.getInt("asignaturaId") ?: 1
            MaterialesScreen(
                asignaturaId = asigId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Progreso.route) {
            ProgresoScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.Consulta.route) {
            ConsultaScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Docente
        composable(Screen.DocenteHome.route) {
            DocenteHomeScreen(
                onNavigateToConsultas = { navController.navigate(Screen.DocenteConsultas.route) },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.DocenteConsultas.route) {
            DocenteConsultasScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}\n