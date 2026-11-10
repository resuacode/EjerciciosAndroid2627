package es.resuacode.todo.ui.screens.add

import androidx.lifecycle.ViewModel
import es.resuacode.todo.ui.screens.main.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddUiState(
    val newItemText: String = "",
)

class AddViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AddUiState())
    val uiState = _uiState.asStateFlow()

    fun updateNewItemText(newText: String) {
        _uiState.update { _uiState.value.copy(newItemText = newText) }
    }

}
