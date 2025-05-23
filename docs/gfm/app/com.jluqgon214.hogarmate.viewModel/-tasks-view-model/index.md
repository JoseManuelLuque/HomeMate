//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[TasksViewModel](index.md)

# TasksViewModel

[androidJvm]\
class [TasksViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar las tareas de los usuarios.

## Constructors

| | |
|---|---|
| [TasksViewModel](-tasks-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [adminDialog](admin-dialog.md) | [androidJvm]<br>val [adminDialog](admin-dialog.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [description](description.md) | [androidJvm]<br>val [description](description.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [showDialog](show-dialog.md) | [androidJvm]<br>val [showDialog](show-dialog.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [tareas](tareas.md) | [androidJvm]<br>val [tareas](tareas.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;&gt; |
| [tareasCargando](tareas-cargando.md) | [androidJvm]<br>val [tareasCargando](tareas-cargando.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [tareasCompletadas](tareas-completadas.md) | [androidJvm]<br>val [tareasCompletadas](tareas-completadas.md): MutableStateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;&gt; |
| [tareasPendientes](tareas-pendientes.md) | [androidJvm]<br>val [tareasPendientes](tareas-pendientes.md): MutableStateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;&gt; |
| [usuario](usuario.md) | [androidJvm]<br>val [usuario](usuario.md): MutableStateFlow&lt;[UsuarioConTareasDTO](../../com.jluqgon214.hogarmate.model.DTO/-usuario-con-tareas-d-t-o/index.md)?&gt; |

## Functions

| Name | Summary |
|---|---|
| [actualizarEstadoTarea](actualizar-estado-tarea.md) | [androidJvm]<br>fun [actualizarEstadoTarea](actualizar-estado-tarea.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [agregarTarea](agregar-tarea.md) | [androidJvm]<br>fun [agregarTarea](agregar-tarea.md)() |
| [agregarTareaUsuario](agregar-tarea-usuario.md) | [androidJvm]<br>fun [agregarTareaUsuario](agregar-tarea-usuario.md)(idUsuario: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |
| [eliminarTarea](eliminar-tarea.md) | [androidJvm]<br>fun [eliminarTarea](eliminar-tarea.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |
| [filtrarTareas](filtrar-tareas.md) | [androidJvm]<br>fun [filtrarTareas](filtrar-tareas.md)() |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [obtenerTareas](obtener-tareas.md) | [androidJvm]<br>fun [obtenerTareas](obtener-tareas.md)() |
| [setAdminDialog](set-admin-dialog.md) | [androidJvm]<br>fun [setAdminDialog](set-admin-dialog.md)(show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)) |
| [setDescription](set-description.md) | [androidJvm]<br>fun [setDescription](set-description.md)(description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setShowDialog](set-show-dialog.md) | [androidJvm]<br>fun [setShowDialog](set-show-dialog.md)(show: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)) |
| [setUsuario](set-usuario.md) | [androidJvm]<br>fun [setUsuario](set-usuario.md)(usuario: [UsuarioConTareasDTO](../../com.jluqgon214.hogarmate.model.DTO/-usuario-con-tareas-d-t-o/index.md)) |
