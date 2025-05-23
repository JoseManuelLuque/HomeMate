//[app](../../index.md)/[com.jluqgon214.hogarmate.screens](index.md)/[AdminScreen](-admin-screen.md)

# AdminScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AdminScreen](-admin-screen.md)(adminViewModel: [AdminViewModel](../com.jluqgon214.hogarmate.viewModel/-admin-view-model/index.md), paddingValues: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html), tasksViewModel: [TasksViewModel](../com.jluqgon214.hogarmate.viewModel/-tasks-view-model/index.md), profileViewModel: [ProfileViewModel](../com.jluqgon214.hogarmate.viewModel/-profile-view-model/index.md))

# Pantalla de administración de usuarios y tareas.

Esta pantalla permite a un administrador gestionar usuarios y sus tareas asociadas. Incluye un diálogo para añadir tareas, un indicador de carga y una lista de usuarios con sus tareas.

#### Parameters

androidJvm

| | |
|---|---|
| paddingValues | Espaciado externo aplicado a los elementos de la pantalla, proviene del Scaffold principal en AppNavigation. |
| adminViewModel | ViewModel que gestiona la lógica de administración de usuarios. |
| tasksViewModel | ViewModel que gestiona la lógica de las tareas. |
| profileViewModel | ViewModel que gestiona la información del perfil del usuario actual. |
