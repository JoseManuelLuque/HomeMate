package com.jluqgon214.hogarmate.service

import com.jluqgon214.hogarmate.model.DTO.CrearTareaDTO
import com.jluqgon214.hogarmate.model.DTO.UsuarioConTareasDTO
import com.jluqgon214.hogarmate.model.LoginRequest
import com.jluqgon214.hogarmate.model.LoginResponse
import com.jluqgon214.hogarmate.model.RegisterRequest
import com.jluqgon214.hogarmate.model.RegisterResponse
import com.jluqgon214.hogarmate.model.Tarea
import com.jluqgon214.hogarmate.model.Usuario
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // USUARIOS

    @POST("/usuarios/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/usuarios/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("/usuarios/admin")
    fun comprobarAdmin(): Call<Boolean>

    @GET("/usuarios/me")
    fun obtenerUsuario(): Call<Usuario>

    @PUT("/usuarios/update")
    fun actualizarUsuario(@Body usuario: Usuario): Call<Usuario>

    @GET("/usuarios/tareas")
    fun obtenerUsuariosConTareas(): Call<List<UsuarioConTareasDTO>>


    // TAREAS

    @GET("/tareas/usuario")
    fun obtenerTareasUsuario(): Call<List<Tarea>>

    @POST("/tareas/crear")
    fun crearTarea(@Body tarea: CrearTareaDTO): Call<Tarea>

    @DELETE("/tareas/delete/{id}")
    suspend fun eliminarTarea(@Path("id") id: String?): Call<Unit>

    @PUT("/tareas/update/status/{id}")
    suspend fun actualizarEstadoTarea(@Path("id") id: String?): Call<Tarea>

    @POST("/tareas/crear/usuario/{idUsuario}")
    fun crearTareaUsuario(@Path("idUsuario") idUsuario: String?, @Body tarea: CrearTareaDTO): Call<Tarea>
}