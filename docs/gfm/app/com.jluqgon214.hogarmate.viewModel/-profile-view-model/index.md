//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[ProfileViewModel](index.md)

# ProfileViewModel

[androidJvm]\
class [ProfileViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar el perfil del usuario.

## Constructors

| | |
|---|---|
| [ProfileViewModel](-profile-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>val [email](email.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt; |
| [showEditDialog](show-edit-dialog.md) | [androidJvm]<br>val [showEditDialog](show-edit-dialog.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [username](username.md) | [androidJvm]<br>val [username](username.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [usuario](usuario.md) | [androidJvm]<br>val [usuario](usuario.md): StateFlow&lt;[Usuario](../../com.jluqgon214.hogarmate.model/-usuario/index.md)?&gt; |

## Functions

| Name | Summary |
|---|---|
| [actualizarUsuario](actualizar-usuario.md) | [androidJvm]<br>fun [actualizarUsuario](actualizar-usuario.md)()<br>Crea un objeto `Usuario` con los datos actualizados y realiza una llamada a la API para actualizar la información. Actualiza el flujo `_usuario` si la operación es exitosa o `_errorMessage` en caso de error. |
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [isFormValid](is-form-valid.md) | [androidJvm]<br>fun [isFormValid](is-form-valid.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)<br>Comprueba si el nombre de usuario y el correo electrónico son válidos. |
| [obtenerUsuario](obtener-usuario.md) | [androidJvm]<br>fun [obtenerUsuario](obtener-usuario.md)()<br>Realiza una llamada a la API para obtener los datos del usuario y actualiza el flujo de estado `_usuario`. En caso de error, actualiza `_errorMessage`. |
| [setEmail](set-email.md) | [androidJvm]<br>fun [setEmail](set-email.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setShowEditDialog](set-show-edit-dialog.md) | [androidJvm]<br>fun [setShowEditDialog](set-show-edit-dialog.md)(value: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)) |
| [setUsername](set-username.md) | [androidJvm]<br>fun [setUsername](set-username.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
