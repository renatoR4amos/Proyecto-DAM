package com.example.repositorioesucar.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repositorioesucar.model.AsignaturaMock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToMateriales: (Int) -> Unit,
    onNavigateToProgreso: () -> Unit,
    onNavigateToConsulta: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    var semestreSeleccionado by remember { mutableStateOf(1) }
    val asignaturas = AsignaturaMock.listaAsignaturas.filter { it.semestre == semestreSeleccionado }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ESUCAR - Mis Asignaturas") },
                actions = {
                    TextButton(onClick = onNavigateToProfile) { Text("Perfil", color = MaterialTheme.colorScheme.onPrimary) }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    label = { Text("Materias") },
                    icon = { Text("📚") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToProgreso,
                    label = { Text("Progreso") },
                    icon = { Text("📊") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToConsulta,
                    label = { Text("Consultas") },
                    icon = { Text("✉️") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Selector de Semestre (1 al 4)
            TabRow(selectedTabIndex = semestreSeleccionado - 1) {
                for (s in 1..4) {
                    Tab(
                        selected = semestreSeleccionado == s,
                        onClick = { semestreSeleccionado = s },
                        text = { Text("Sem $s") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(asignaturas) { asig ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToMateriales(asig.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(asig.codigo, style = MaterialTheme.typography.labelSmall)
                            Text(asig.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Área: ${asig.area}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}\n