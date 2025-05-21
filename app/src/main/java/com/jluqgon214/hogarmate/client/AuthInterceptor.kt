package com.jluqgon214.hogarmate.client

import okhttp3.Interceptor
import okhttp3.Response

/**
 * # Interceptor de autenticación para añadir un token JWT a las solicitudes HTTP.
 *
 * Este interceptor verifica si hay un token disponible a través del proveedor de tokens
 * y, si está presente, lo incluye en el encabezado de autorización de la solicitud.
 *
 * @property tokenProvider Función que proporciona el token JWT actual o `null` si no está disponible.
 */
class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {

    /**
     * ### Intercepta las solicitudes HTTP para añadir el encabezado de autorización si el token está disponible.
     *
     * @param chain Cadena de interceptores que permite continuar con la solicitud.
     * @return Respuesta HTTP resultante después de procesar la solicitud.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request() // Solicitud original.
        val token = tokenProvider() // Obtiene el token JWT del proveedor.

        return if (token != null) {
            // Si el token está disponible, crea una nueva solicitud con el encabezado de autorización.
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token") // Añade el token al encabezado.
                .build()
            chain.proceed(newRequest) // Continúa con la nueva solicitud.
        } else {
            chain.proceed(request) // Continúa con la solicitud original si no hay token.
        }
    }
}