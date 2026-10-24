package com.example.repositorioesucar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var rolSeleccionado by remember { mutableStateOf("ESTUDIANTE") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("ESUCAR - Acceso") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Repositorio Móvil ESUCAR", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo Institucional") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Selector rápido para demo
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = rolSeleccionado == "ESTUDIANTE",
                    onClick = { rolSeleccionado = "ESTUDIANTE" }
                )
                Text("Alumno")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = rolSeleccionado == "DOCENTE",
                    onClick = { rolSeleccionado = "DOCENTE" }
                )
                Text("Docente")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { onLoginSuccess(rolSeleccionado) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = onNavigateToRegister) {
                Text("¿No tienes cuenta? Regístrate aquí")
            }
        }
    }
}\n