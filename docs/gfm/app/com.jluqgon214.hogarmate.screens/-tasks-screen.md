//[app](../../index.md)/[com.jluqgon214.hogarmate.screens](index.md)/[TasksScreen](-tasks-screen.md)

# TasksScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TasksScreen](-tasks-screen.md)(tasksViewModel: [TasksViewModel](../com.jluqgon214.hogarmate.viewModel/-tasks-view-model/index.md), paddingValues: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html), navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html))

# Pantalla de tareas.

Esta pantalla muestra las tareas pendientes y completadas del usuario. Permite realizar acciones como actualizar el estado de una tarea, eliminarla o añadir nuevas tareas mediante un diálogo. También incluye funcionalidad de &quot;pull-to-refresh&quot; para recargar las tareas.

#### Parameters

androidJvm

| | |
|---|---|
| tasksViewModel | ViewModel que gestiona la lógica de las tareas. |
| paddingValues | Espaciado externo aplicado a los elementos de la pantalla. |
| navController | Controlador de navegación para gestionar la navegación entre pantallas. |
