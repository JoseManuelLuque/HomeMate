//[app](../../../index.md)/[com.jluqgon214.hogarmate.client](../index.md)/[RetrofitClient](index.md)

# RetrofitClient

[androidJvm]\
object [RetrofitClient](index.md)

# Objeto que proporciona una instancia de Retrofit configurada para interactuar con la API.

Este cliente utiliza un interceptor de autenticación para incluir el token JWT en las solicitudes y configura tiempos de espera personalizados para las conexiones, lecturas y escrituras.

## Properties

| Name | Summary |
|---|---|
| [instance](instance.md) | [androidJvm]<br>val [instance](instance.md): [ApiService](../../com.jluqgon214.hogarmate.service/-api-service/index.md)<br>Instancia única de `ApiService` creada de forma perezosa. |
