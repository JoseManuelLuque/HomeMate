//[app](../../../index.md)/[com.jluqgon214.hogarmate.viewModel](../index.md)/[AdminViewModel](index.md)/[obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md)

# obtenerUsuariosConTareas

[androidJvm]\
fun [obtenerUsuariosConTareas](obtener-usuarios-con-tareas.md)()

###  Obtiene la lista de usuarios con sus tareas desde la API.

Realiza una llamada a la API para obtener los usuarios con tareas asignadas, excluyendo al usuario actual, y actualiza el estado del flujo `_usuariosConTareas`. También actualiza el estado de carga en `_usuariosCargando`.
