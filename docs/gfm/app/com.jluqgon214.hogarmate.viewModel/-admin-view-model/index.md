//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[AdminViewModel](index.md)

# AdminViewModel

[androidJvm]\
class [AdminViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar la administración de usuarios y sus tareas.

## Constructors

| | |
|---|---|
| [AdminViewModel](-admin-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [idUsuarioActual](id-usuario-actual.md) | [androidJvm]<br>val [idUsuarioActual](id-usuario-actual.md): MutableStateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt; |
| [isAdmin](is-admin.md) | [androidJvm]<br>val [isAdmin](is-admin.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [usuariosCargando](usuarios-cargando.md) | [androidJvm]<br>val [usuariosCargando](usuarios-cargando.md): MutableStateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [usuariosConTareas](usuarios-con-tareas.md) | [androidJvm]<br>val [usuariosConTareas](usuarios-con-tareas.md): MutableStateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UsuarioConTareasDTO](../../com.jluqgon214.hogarmate.model.DTO/-usuario-con-tareas-d-t-o/index.md)&gt;&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [comprobarAdmin](comprobar-admin.md) | [androidJvm]<br>fun [comprobarAdmin](comprobar-admin.md)()<br>Realiza una llamada a la API para verificar si el usuario tiene permisos de administrador y actualiza el estado del flujo `_isAdmin` en consecuencia. |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md) | [androidJvm]<br>fun [obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md)()<br>Realiza una llamada a la API para obtener los usuarios con tareas asignadas, excluyendo al usuario actual, y actualiza el estado del flujo `_usuariosConTareas`. También actualiza el estado de carga en `_usuariosCargando`. |
| [setUsuarioActualId](set-usuario-actual-id.md) | [androidJvm]<br>fun [setUsuarioActualId](set-usuario-actual-id.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html))<br>Establece el ID del usuario actual. |
