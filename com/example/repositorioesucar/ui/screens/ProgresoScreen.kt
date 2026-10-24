package com.example.repositorioesucar.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repositorioesucar.ui.components.ProgressBarEstudio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgresoScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Progreso Académico") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("← Volver") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            ProgressBarEstudio(completados = 14, total = 48)
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Resumen de Cumplimiento", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• Semestre 1: 100% completado")
                    Text("• Semestre 2: 45% completado")
                    Text("• Semestre 3: Pendiente")
                    Text("• Semestre 4: Pendiente")
                }
            }
        }
    }
}\n