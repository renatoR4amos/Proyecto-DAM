# BITÁCORA Y GUÍA INTEGRAL DE TRABAJO: REPOSITORIO MÓVIL ESUCAR

## 1. Resumen Ejecutivo (Para Compartir)

```text
PROYECTO: Repositorio Móvil ESUCAR (Segunda Entrega)
ARQUITECTURA: MVVM (Model-View-ViewModel) con Jetpack Compose y SQLite local (Room)
ESTADO: Offline-first (autenticación y catálogo 100% operativos sin internet)

OBJETIVOS OBLIGATORIOS DE LA PAUTA:
1. Base de datos Room funcional: Registro e Inicio de sesión persistente en SQLite.
2. Roles diferenciados: Flujos separados para Estudiante y Docente.
3. Catálogo académico: 24 asignaturas organizadas en 4 semestres.
4. Formularios secundarios: Notificación Toast/Dialog "Para una futura actualización".
5. Recursos nativos: Selector de foto de perfil desde galería/cámara.
6. Demostración: Video funcional de máximo 90 segundos.
```

---

## 2. Justificación Técnica: Cero Código Redundante

Cada archivo y carpeta responde a una exigencia explícita de la pauta de evaluación. No existen dependencias ni archivos sobrantes:

* **`data/local/` (Room):** Exigido para cumplir con el requisito de persistencia offline de credenciales y marcas de lectura.
* **`data/repository/`:** Exigido por el estándar de arquitectura de las Guías 7 y 10 para desacoplar base de datos de la interfaz.
* **`model/`:** Modela estrictamente las entidades del caso de estudio (24 asignaturas, materiales de lectura, consultas y usuarios).
* **`navigation/`:** Exigido para gestionar el flujo `NavHost` y restringir pantallas según el rol logueado.
* **`ui/components/`:** Aisla el requerimiento de la Guía 13 (recursos nativos / galería) y la barra de avance.
* **`ui/screens/`:** Corresponde exactamente a las vistas obligatorias por rol (5 vistas de Estudiante, 2 de Docente, 2 de Autenticación).
* **`viewmodel/`:** Cumple con la reactividad basada en `StateFlow` y `collectAsState()` de las Guías 11 y 12.

---

## 3. Mapa de Paquetes y Función Técnica

| Paquete / Carpeta | Función Técnica Específica | Archivos Base Integrados |
| :--- | :--- | :--- |
| **`data/local`** | Manejo de SQLite local (Room). Crea la base de datos `esucar_database`, gestiona tablas y operaciones CRUD. | `AppDatabase.kt`, `UserDao.kt`, `ProgresoDao.kt`, `UserEntity.kt`, `ProgresoEntity.kt` |
| **`data/repository`** | Abstracción de datos. Provee una API limpia a los ViewModels para consultar usuarios o asignaturas. | `UserRepository.kt`, `AsignaturaRepository.kt` |
| **`model`** | Modelos de dominio puros (DTOs) y el generador de las 24 asignaturas distribuidas en 4 semestres. | `User.kt`, `Asignatura.kt`, `MaterialEstudio.kt`, `ConsultaDocente.kt`, `AsignaturaMock.kt` |
| **`navigation`** | Enrutador central. Declara las rutas tipo-seguras (`sealed class Screen`) y el enrutamiento condicional. | `Screen.kt`, `AppNavigation.kt` |
| **`ui/components`** | Componentes reutilizables: selector de imágenes nativo (`ActivityResultContracts`) y barra de progreso. | `ImagenInteligente.kt`, `ProgressBarEstudio.kt` |
| **`ui/screens`** | Interfaces gráficas construidas en Jetpack Compose para los perfiles Estudiante y Docente. | `LoginScreen.kt`, `RegisterScreen.kt`, `HomeScreen.kt`, `MaterialesScreen.kt`, `ProgresoScreen.kt`, `ConsultaScreen.kt`, `ProfileScreen.kt`, `DocenteHomeScreen.kt`, `DocenteConsultasScreen.kt` |
| **`ui/theme`** | Sistema de diseño Material 3 con colores institucionales (Verde ESUCAR #005A36 y Dorado #D4AF37). | `Color.kt`, `Theme.kt` |
| **`viewmodel`** | Lógica de presentación y control de estado reactivo mediante `StateFlow` desacoplado del ciclo de vida. | `AuthViewModel.kt`, `AsignaturaViewModel.kt`, `ProgresoViewModel.kt`, `ConsultaViewModel.kt`, `ProfileViewModel.kt` |
| **`Raíz`** | Punto de entrada del sistema Android que inicializa el tema y el `NavHost`. | `MainActivity.kt` |

---

## 4. Asignación Operativa de Tareas (2 Desarrolladores)

### 👤 DESARROLLADOR 1: Datos, Autenticación y Flujo Docente
**Responsabilidad principal:** Garantizar la persistencia offline en Room y los flujos de gestión.

* **Tarea 1.1 (Persistencia SQLite con Room):**
  * Probar y asegurar la inicialización de `AppDatabase.kt`.
  * Validar consultas en `UserDao.kt` (registro único e inicio de sesión por correo y clave).
* **Tarea 1.2 (Autenticación Reactiva):**
  * Conectar `AuthViewModel.kt` con la UI.
  * Ajustar `LoginScreen.kt` y `RegisterScreen.kt` (manejo de errores de credenciales y campos vacíos).
* **Tarea 1.3 (Recursos Nativos y Perfil):**
  * Verificar en `ImagenInteligente.kt` y `ProfileScreen.kt` la persistencia en memoria de la foto seleccionada de la galería.
  * Implementar el cierre de sesión (`onLogout`) retornando a `Screen.Login`.
* **Tarea 1.4 (Panel Docente):**
  * Ajustar `DocenteHomeScreen.kt` para mostrar las asignaturas que dicta el profesor.
  * Configurar `DocenteConsultasScreen.kt` con el botón de respuesta y Toast *"Para una futura actualización"*.

---

### 👤 DESARROLLADOR 2: Catálogo Académico, Portal Estudiante y Progreso
**Responsabilidad principal:** Garantizar la experiencia académica del estudiante y visualización de recursos.

* **Tarea 2.1 (Estructuración de Asignaturas):**
  * Revisar `AsignaturaMock.kt` para asegurar la coherencia de las 24 materias (6 por semestre) y sus unidades.
  * Enlazar `AsignaturaRepository.kt` con `AsignaturaViewModel.kt`.
* **Tarea 2.2 (Portal del Alumno - Vistas de Contenido):**
  * Ajustar `HomeScreen.kt` con pestañas interactivas de Semestre 1 a 4.
  * Configurar `MaterialesScreen.kt` para mostrar recursos descargables (PDF, Guías) y el botón de descarga con aviso Toast.
* **Tarea 2.3 (Barra de Avance y Progreso):**
  * Conectar `ProgresoViewModel.kt` con `ProgressBarEstudio.kt` en `ProgresoScreen.kt`.
  * Permitir marcar lecturas como *"Estudiado"* incrementando el contador visual.
* **Tarea 2.4 (Formulario de Consultas):**
  * Ajustar `ConsultaScreen.kt` para capturar la duda del alumno y mostrar el mensaje obligatorio: *"Mensaje enviado. Funcionalidad completa disponible para una futura actualización"*.

---

## 5. Protocolo de Integración y Pruebas

1. **Importación a Android Studio:**
   * Crear proyecto *Empty Activity* con paquete `com.example.repositorioesucar`.
   * Copiar el contenido de `com/example/repositorioesucar` a `app/src/main/java/com/example/repositorioesucar`.
2. **Punto de Encuentro:**
   * Ambos desarrolladores respetan las rutas de [`Screen.kt`](file:///C:/Users/renat/Desktop/Proyecto%20DAM/com/example/repositorioesucar/navigation/Screen.kt) y el tema de [`Theme.kt`](file:///C:/Users/renat/Desktop/Proyecto%20DAM/com/example/repositorioesucar/ui/theme/Theme.kt).
3. **Grabación de Video Demostrativo (Máximo 90s):**
   * Segundos 0-25: Registro de usuario y Login con Room (validación offline).
   * Segundos 26-50: Navegación de Alumno por las 24 asignaturas y marca de lectura en Progreso.
   * Segundos 51-65: Envío de consulta con aviso de actualización futura y cambio de foto de perfil.
   * Segundos 66-90: Cierre de sesión, ingreso como Docente y visualización de cátedras y consultas.
