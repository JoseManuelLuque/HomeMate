//[app](../../index.md)/[com.jluqgon214.hogarmate.components](index.md)/[UsuarioItem](-usuario-item.md)

# UsuarioItem

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [UsuarioItem](-usuario-item.md)(usuario: [UsuarioConTareasDTO](../com.jluqgon214.hogarmate.model.DTO/-usuario-con-tareas-d-t-o/index.md), tasksViewModel: [TasksViewModel](../com.jluqgon214.hogarmate.viewModel/-tasks-view-model/index.md), adminViewModel: [AdminViewModel](../com.jluqgon214.hogarmate.viewModel/-admin-view-model/index.md))

# Componente que representa un elemento de usuario con sus tareas asociadas.

Este componente muestra información básica del usuario, como su nombre de usuario y correo electrónico, y permite expandir para ver y gestionar las tareas asociadas al usuario.

#### Parameters

androidJvm

| | |
|---|---|
| usuario | Objeto que contiene la información del usuario y sus tareas. |
| tasksViewModel | ViewModel que gestiona las acciones relacionadas con las tareas. |
| adminViewModel | ViewModel que gestiona las acciones relacionadas con los usuarios y sus tareas. |
