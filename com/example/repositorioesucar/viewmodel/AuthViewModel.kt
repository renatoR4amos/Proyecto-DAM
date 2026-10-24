package com.example.repositorioesucar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.repositorioesucar.data.local.AppDatabase
import com.example.repositorioesucar.data.local.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val correo: String = "",
    val clave: String = "",
    val nombre: String = "",
    val rol: String = "ESTUDIANTE",
    val usuarioLogueado: UserEntity? = null,
    val mensajeError: String? = null,
    val estaCargando: Boolean = false
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onCorreoChange(valor: String) = _uiState.update { it.copy(correo = valor) }
    fun onClaveChange(valor: String) = _uiState.update { it.copy(clave = valor) }
    fun onNombreChange(valor: String) = _uiState.update { it.copy(nombre = valor) }
    fun onRolChange(valor: String) = _uiState.update { it.copy(rol = valor) }

    fun login(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(estaCargando = true) }
            val user = userDao.login(_uiState.value.correo, _uiState.value.clave)
            if (user != null) {
                _uiState.update { it.copy(usuarioLogueado = user, estaCargando = false, mensajeError = null) }
                onSuccess(user.rol)
            } else {
                _uiState.update { it.copy(mensajeError = "Credenciales incorrectas", estaCargando = false) }
            }
        }
    }

    fun registrar(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val nuevo = UserEntity(
                nombre = _uiState.value.nombre,
                correo = _uiState.value.correo,
                clave = _uiState.value.clave,
                rol = _uiState.value.rol
            )
            userDao.registrarUsuario(nuevo)
            onSuccess()
        }
    }
}\n