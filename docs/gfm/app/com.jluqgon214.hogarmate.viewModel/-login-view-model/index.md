//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[LoginViewModel](index.md)

# LoginViewModel

[androidJvm]\
class [LoginViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

# ViewModel para gestionar el inicio de sesión del usuario.

Esta clase utiliza flujos de estado (`StateFlow`) para manejar datos reactivos como el correo electrónico, la contraseña, la respuesta de inicio de sesión, mensajes de error y el estado de carga.

## Constructors

| | |
|---|---|
| [LoginViewModel](-login-view-model.md) | [androidJvm]<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md)<br>Este objeto permite almacenar y acceder al token JWT sin necesidad de instanciar la clase `LoginViewModel`. |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>val [email](email.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?&gt; |
| [isLoading](is-loading.md) | [androidJvm]<br>val [isLoading](is-loading.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [loginCorrecto](login-correcto.md) | [androidJvm]<br>val [loginCorrecto](login-correcto.md): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [loginResponse](login-response.md) | [androidJvm]<br>val [loginResponse](login-response.md): StateFlow&lt;[LoginResponse](../../com.jluqgon214.hogarmate.model/-login-response/index.md)?&gt; |
| [password](password.md) | [androidJvm]<br>val [password](password.md): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-theme-view-model/index.md#383812252%2FFunctions%2F-912451524)(closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html))<br>fun [addCloseable](../-theme-view-model/index.md#1722490497%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), closeable: [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)) |
| [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524) : [AutoCloseable](https://developer.android.com/reference/kotlin/java/lang/AutoCloseable.html)&gt; [getCloseable](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): [T](../-theme-view-model/index.md#1102255800%2FFunctions%2F-912451524)? |
| [login](login.md) | [androidJvm]<br>fun [login](login.md)()<br>Esta función realiza una llamada a la API para autenticar al usuario utilizando el correo electrónico y la contraseña proporcionados. Actualiza los flujos de estado con la respuesta o los errores. |
| [setEmail](set-email.md) | [androidJvm]<br>fun [setEmail](set-email.md)(email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
| [setErrorMessage](set-error-message.md) | [androidJvm]<br>fun [setErrorMessage](set-error-message.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |
| [setJWT](set-j-w-t.md) | [androidJvm]<br>fun [setJWT](set-j-w-t.md)(jwt: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |
| [setLoginCorrecto](set-login-correcto.md) | [androidJvm]<br>fun [setLoginCorrecto](set-login-correcto.md)(value: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)) |
| [setPassword](set-password.md) | [androidJvm]<br>fun [setPassword](set-password.md)(password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)) |
