package es.resuacode.todo.ui.screens.main

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import es.resuacode.todo.data.TodoItem
import es.resuacode.todo.data.todoIniciales
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class UiState(
    val todoList : List<TodoItem> = listOf<TodoItem>(),
    // val newItemText : String = "" // esto ahora está en el viewModel de add
)

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(todoList = todoIniciales)
    }

    /*
    fun updateNewItemText(newText: String) {
        _uiState.update { _uiState.value.copy(newItemText = newText) }
    }
    */


    // Versión con un único uiState y machacando la lista
    /*
    fun addItem(context: Context) {
        if (_uiState.value.newItemText.isBlank()) {
            Toast.makeText(context, "No puedes añadir tareas vacías", Toast.LENGTH_SHORT).show()
        } else {
            _uiState.update {
                _uiState.value.copy(
                    todoList = _uiState.value.todoList + TodoItem(_uiState.value.newItemText),
                    newItemText = ""
                )
            }
        }
    }

     */

    fun addItem(tarea: String) {
        _uiState.value = _uiState.value.copy(todoList = _uiState.value.todoList + TodoItem(tarea, false))
    }

    fun detele(item: TodoItem) {
        _uiState.value = _uiState.value.copy(todoList = _uiState.value.todoList.toMutableStateList().apply { remove(item) })
    }

    fun cambiarCompletado(item: TodoItem) {
        _uiState.value = _uiState.value.copy(
                todoList = _uiState.value.todoList.map {
                    if (it.tarea == item.tarea) {
                        it.copy( completado = !it.completado)
                    } else {
                        it
                    }
                }
            )
    }
}