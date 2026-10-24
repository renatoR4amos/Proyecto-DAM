package com.example.repositorioesucar.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repositorioesucar.ui.components.ImagenInteligente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("← Volver") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagenInteligente(
                imageUri = photoUri,
                onImageSelected = { photoUri = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Suboficial Alumno", style = MaterialTheme.typography.titleLarge)
            Text("alumno.esucar@carabineros.cl", style = MaterialTheme.typography.bodyMedium)
            Text("Escuela de Suboficiales de Carabineros", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}\n