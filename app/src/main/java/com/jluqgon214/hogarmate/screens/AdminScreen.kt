package com.jluqgon214.hogarmate.screens

    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.zIndex
    import com.jluqgon214.hogarmate.components.AddTaskDialog
    import com.jluqgon214.hogarmate.components.UsuarioItem
    import com.jluqgon214.hogarmate.viewModel.AdminViewModel
    import com.jluqgon214.hogarmate.viewModel.ProfileViewModel
    import com.jluqgon214.hogarmate.viewModel.TasksViewModel

    /**
     * # Pantalla de administración de usuarios y tareas.
     *
     * Esta pantalla permite a un administrador gestionar usuarios y sus tareas asociadas.
     * Incluye un diálogo para añadir tareas, un indicador de carga y una lista de usuarios con sus tareas.
     *
     * @param paddingValues Espaciado externo aplicado a los elementos de la pantalla, proviene del Scaffold principal en AppNavigation.
     * @param adminViewModel ViewModel que gestiona la lógica de administración de usuarios.
     * @param tasksViewModel ViewModel que gestiona la lógica de las tareas.
     * @param profileViewModel ViewModel que gestiona la información del perfil del usuario actual.
     */
    @Composable
    fun AdminScreen(
        adminViewModel: AdminViewModel,
        paddingValues: PaddingValues,
        tasksViewModel: TasksViewModel,
        profileViewModel: ProfileViewModel
    ) {
        // Estado que contiene la lista de usuarios con sus tareas.
        val usuariosConTareas by adminViewModel.usuariosConTareas.collectAsState()

        // Estado que indica si el diálogo de administración está abierto.
        val adminDialog by tasksViewModel.adminDialog.collectAsState()

        // Estado que contiene el usuario seleccionado para asignar tareas.
        val usuario by tasksViewModel.usuario.collectAsState()

        // Estado que indica si los usuarios están cargando.
        val usuariosCargando by adminViewModel.usuariosCargando.collectAsState()

        // Efecto lanzado al inicializar la pantalla para obtener el usuario actual y cargar los datos necesarios.
        LaunchedEffect(Unit) {
            profileViewModel.obtenerUsuario()
            // Observa el usuario actual y, si está disponible, establece su ID en el ViewModel de administración.
            profileViewModel.usuario.collect { usuario ->
                usuario?._id?.let { id ->
                    adminViewModel.setUsuarioActualId(id)
                    adminViewModel.obtenerUsuariosConTareas()
                }
            }
        }

        // Muestra el diálogo para añadir tareas si está activo.
        if (adminDialog) {
            AddTaskDialog(
                tasksViewModel = tasksViewModel,
                adminViewModel = adminViewModel,
                usuario = usuario,
            )
        }

        // Muestra un indicador de carga si los usuarios están cargando.
        if (usuariosCargando) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .zIndex(1f) // Asegura que el indicador esté encima de otros elementos.
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Lista de usuarios con sus tareas.
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            items(usuariosConTareas) { usuario ->
                UsuarioItem(usuario, tasksViewModel, adminViewModel)
            }
        }
    }