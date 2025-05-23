//[app](../../../index.md)/[com.jluqgon214.hogarmate.client](../index.md)/[AuthInterceptor](index.md)

# AuthInterceptor

[androidJvm]\
class [AuthInterceptor](index.md)(tokenProvider: () -&gt; [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) : Interceptor

# Interceptor de autenticación para añadir un token JWT a las solicitudes HTTP.

Este interceptor verifica si hay un token disponible a través del proveedor de tokens y, si está presente, lo incluye en el encabezado de autorización de la solicitud.

## Constructors

| | |
|---|---|
| [AuthInterceptor](-auth-interceptor.md) | [androidJvm]<br>constructor(tokenProvider: () -&gt; [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) |

## Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | [androidJvm]<br>open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response |
