package es.resuacode.todo.data

data class TodoItem (
    val tarea: String,
    var completado: Boolean = false
)