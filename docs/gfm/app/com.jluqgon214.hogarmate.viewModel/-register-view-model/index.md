//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[RegisterViewModel](index.md)

# RegisterViewModel

[androidJvm]\
class [RegisterViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar el registro de usuarios.

Proporciona flujos de estado para los datos del formulario de registro y el manejo de respuestas y errores.

## Constructors

| | |
|---|---|
| [RegisterViewModel](-register-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>val [email](email.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt; |
| [password](password.md) | [androidJvm]<br>val [password](password.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [passwordRepeat](password-repeat.md) | [androidJvm]<br>val [passwordRepeat](password-repeat.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [registerResponse](register-response.md) | [androidJvm]<br>val [registerResponse](register-response.md): StateFlow&lt;[RegisterResponse](../../com.jluqgon214.hogarmate.model/-register-response/index.md)?&gt; |
| [username](username.md) | [androidJvm]<br>val [username](username.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [register](register.md) | [androidJvm]<br>fun [register](register.md)()<br>Actualiza los flujos de estado con la respuesta o el mensaje de error correspondiente. |
| [setEmail](set-email.md) | [androidJvm]<br>fun [setEmail](set-email.md)(email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setPassword](set-password.md) | [androidJvm]<br>fun [setPassword](set-password.md)(password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setRepeatPassword](set-repeat-password.md) | [androidJvm]<br>fun [setRepeatPassword](set-repeat-password.md)(password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setUsername](set-username.md) | [androidJvm]<br>fun [setUsername](set-username.md)(username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
