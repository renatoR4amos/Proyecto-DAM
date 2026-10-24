package com.example.repositorioesucar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repositorioesucar.model.AsignaturaMock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocenteHomeScreen(
    onNavigateToConsultas: () -> Unit,
    onLogout: () -> Unit
) {
    val asignaturasDocente = AsignaturaMock.listaAsignaturas.take(4)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Docente - ESUCAR") },
                actions = {
                    TextButton(onClick = onLogout) { Text("Salir", color = MaterialTheme.colorScheme.onPrimary) }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToConsultas,
                text = { Text("Bandeja de Consultas") },
                icon = { Text("📬") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Cátedras Asignadas", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(asignaturasDocente) { asig ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(asig.codigo, style = MaterialTheme.typography.labelSmall)
                            Text(asig.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Semestre ${asig.semestre} • ${asig.unidades.size} unidades publicadas")
                        }
                    }
                }
            }
        }
    }
}\n