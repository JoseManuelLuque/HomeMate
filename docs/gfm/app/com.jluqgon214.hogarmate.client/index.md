//[app](../../index.md)/[com.jluqgon214.hogarmate.client](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AuthInterceptor](-auth-interceptor/index.md) | [androidJvm]<br>class [AuthInterceptor](-auth-interceptor/index.md)(tokenProvider: () -&gt; [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?) : Interceptor<br>Este interceptor verifica si hay un token disponible a través del proveedor de tokens y, si está presente, lo incluye en el encabezado de autorización de la solicitud. |
| [RetrofitClient](-retrofit-client/index.md) | [androidJvm]<br>object [RetrofitClient](-retrofit-client/index.md)<br>Este cliente utiliza un interceptor de autenticación para incluir el token JWT en las solicitudes y configura tiempos de espera personalizados para las conexiones, lecturas y escrituras. |
