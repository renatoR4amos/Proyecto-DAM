package com.example.repositorioesucar.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ConsultaUiState(
    val asunto: String = "",
    val mensaje: String = "",
    val enviado: Boolean = false
)

class ConsultaViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConsultaUiState())
    val uiState: StateFlow<ConsultaUiState> = _uiState

    fun onAsuntoChange(valor: String) = _uiState.update { it.copy(asunto = valor) }
    fun onMensajeChange(valor: String) = _uiState.update { it.copy(mensaje = valor) }
    fun enviarConsulta() = _uiState.update { it.copy(enviado = true) }
}\n