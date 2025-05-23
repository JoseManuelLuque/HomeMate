//[app](../../../index.md)/[com.jluqgon214.hogarmate.service](../index.md)/[ApiService](index.md)

# ApiService

[androidJvm]\
interface [ApiService](index.md)

# Interfaz que define los endpoints de la API REST.

Contiene las funciones que se utilizarán para realizar las peticiones a la API.

## Functions

| Name | Summary |
|---|---|
| [actualizarEstadoTarea](actualizar-estado-tarea.md) | [androidJvm]<br>@PUT(value = &quot;/tareas/update/status/{id}&quot;)<br>abstract suspend fun [actualizarEstadoTarea](actualizar-estado-tarea.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): Call&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt; |
| [actualizarUsuario](actualizar-usuario.md) | [androidJvm]<br>@PUT(value = &quot;/usuarios/update&quot;)<br>abstract fun [actualizarUsuario](actualizar-usuario.md)(@Bodyusuario: [Usuario](../../com.jluqgon214.hogarmate.model/-usuario/index.md)): Call&lt;[Usuario](../../com.jluqgon214.hogarmate.model/-usuario/index.md)&gt; |
| [comprobarAdmin](comprobar-admin.md) | [androidJvm]<br>@GET(value = &quot;/usuarios/admin&quot;)<br>abstract fun [comprobarAdmin](comprobar-admin.md)(): Call&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html)&gt; |
| [crearTarea](crear-tarea.md) | [androidJvm]<br>@POST(value = &quot;/tareas/crear&quot;)<br>abstract fun [crearTarea](crear-tarea.md)(@Bodytarea: [CrearTareaDTO](../../com.jluqgon214.hogarmate.model.DTO/-crear-tarea-d-t-o/index.md)): Call&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt; |
| [crearTareaUsuario](crear-tarea-usuario.md) | [androidJvm]<br>@POST(value = &quot;/tareas/crear/usuario/{idUsuario}&quot;)<br>abstract fun [crearTareaUsuario](crear-tarea-usuario.md)(@Path(value = &quot;idUsuario&quot;)idUsuario: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?, @Bodytarea: [CrearTareaDTO](../../com.jluqgon214.hogarmate.model.DTO/-crear-tarea-d-t-o/index.md)): Call&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt; |
| [eliminarTarea](eliminar-tarea.md) | [androidJvm]<br>@DELETE(value = &quot;/tareas/delete/{id}&quot;)<br>abstract suspend fun [eliminarTarea](eliminar-tarea.md)(@Path(value = &quot;id&quot;)id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)?): Call&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html)&gt; |
| [login](login.md) | [androidJvm]<br>@POST(value = &quot;/usuarios/login&quot;)<br>abstract fun [login](login.md)(@Bodyrequest: [LoginRequest](../../com.jluqgon214.hogarmate.model/-login-request/index.md)): Call&lt;[LoginResponse](../../com.jluqgon214.hogarmate.model/-login-response/index.md)&gt; |
| [obtenerTareasUsuario](obtener-tareas-usuario.md) | [androidJvm]<br>@GET(value = &quot;/tareas/usuario&quot;)<br>abstract fun [obtenerTareasUsuario](obtener-tareas-usuario.md)(): Call&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[Tarea](../../com.jluqgon214.hogarmate.model/-tarea/index.md)&gt;&gt; |
| [obtenerUsuario](obtener-usuario.md) | [androidJvm]<br>@GET(value = &quot;/usuarios/me&quot;)<br>abstract fun [obtenerUsuario](obtener-usuario.md)(): Call&lt;[Usuario](../../com.jluqgon214.hogarmate.model/-usuario/index.md)&gt; |
| [obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md) | [androidJvm]<br>@GET(value = &quot;/usuarios/tareas&quot;)<br>abstract fun [obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md)(): Call&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UsuarioConTareasDTO](../../com.jluqgon214.hogarmate.model.DTO/-usuario-con-tareas-d-t-o/index.md)&gt;&gt; |
| [register](register.md) | [androidJvm]<br>@POST(value = &quot;/usuarios/register&quot;)<br>abstract fun [register](register.md)(@Bodyrequest: [RegisterRequest](../../com.jluqgon214.hogarmate.model/-register-request/index.md)): Call&lt;[RegisterResponse](../../com.jluqgon214.hogarmate.model/-register-response/index.md)&gt; |
