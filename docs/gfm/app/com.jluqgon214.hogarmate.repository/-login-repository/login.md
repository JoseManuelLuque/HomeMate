//[app](../../../index.md)/[com.jluqgon214.hogarmate.repository](../index.md)/[LoginRepository](index.md)/[login](login.md)

# login

[androidJvm]\
suspend fun [login](login.md)(username: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)): Response&lt;[LoginResponse](../../com.jluqgon214.hogarmate.model/-login-response/index.md)&gt;

###  Realiza una solicitud de inicio de sesión a la API.

#### Return

Una respuesta de tipo `Response<LoginResponse>` que contiene los datos de la respuesta de la API.

#### Parameters

androidJvm

| | |
|---|---|
| username | Nombre de usuario proporcionado para el inicio de sesión. |
| password | Contraseña proporcionada para el inicio de sesión. |
