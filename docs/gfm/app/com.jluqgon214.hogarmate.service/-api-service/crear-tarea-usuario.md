//[app](../../../index.md)/[com.jluqgon214.hogarmate.service](../index.md)/[ApiService](index.md)/[crearTareaUsuario](crear-tarea-usuario.md)

# crearTareaUsuario

[androidJvm]\

@POST(value = &quot;/tareas/crear/usuario/{idUsuario}&quot;)

abstract fun [crearTareaUsuario](crear-tarea-usuario.md)(@Path(value = &quot;idUsuario&quot;)idUsuario: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, @Bodytarea: [CrearTareaDTO](../../com.jluqgon214.hogarmate.model.DTO/-crear-tarea-d-t-o/index.md)): Call&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;
