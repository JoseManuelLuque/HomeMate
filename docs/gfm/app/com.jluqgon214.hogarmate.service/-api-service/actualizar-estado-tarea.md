//[app](../../../index.md)/[com.jluqgon214.hogarmate.service](../index.md)/[ApiService](index.md)/[actualizarEstadoTarea](actualizar-estado-tarea.md)

# actualizarEstadoTarea

[androidJvm]\

@PUT(value = &quot;/tareas/update/status/{id}&quot;)

abstract suspend fun [actualizarEstadoTarea](actualizar-estado-tarea.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): Call&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;
