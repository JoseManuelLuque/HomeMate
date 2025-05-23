//[app](../../index.md)/[com.jluqgon214.hogarmate.components](index.md)/[CardTarea](-card-tarea.md)

# CardTarea

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CardTarea](-card-tarea.md)(task: [Tarea](../com.jluqgon214.hogarmate.model/-tarea/index.md), assignedUser: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), onComplete: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html), onDelete: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

# Componente que representa una tarjeta de tarea interactiva.

Este componente permite mostrar información de una tarea, asignarla a un usuario, completarla o eliminarla mediante gestos de deslizamiento.

#### Parameters

androidJvm

| | |
|---|---|
| task | Objeto que contiene la información de la tarea. |
| assignedUser | Nombre del usuario asignado a la tarea. |
| onComplete | Callback que se ejecuta al marcar la tarea como completada o deshacer su completado. |
| onDelete | Callback que se ejecuta al eliminar la tarea. |
