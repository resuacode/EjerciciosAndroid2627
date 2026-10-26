package es.resuacode.myapplication.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState : StateFlow<MainScreenState> = _uiState.asStateFlow()

    fun incrementar(isCount1: Boolean) {
        _uiState.update {
            if(isCount1) it.copy(count1 = it.count1 + it.increment1.toInt())
            else it.copy(count2 = it.count2 + it.increment2.toInt())
        }
    }

    fun resetCount(isCount1: Boolean) {
        _uiState.update {
            if(isCount1) it.copy(count1 = 0)
            else it.copy(count2 = 0)
        }
    }

    fun resetCountV2(isCount1: Boolean) {
        _uiState.update {
            if(isCount1) it.copy(count1 = 0, countFinal = it.countFinal - it.count1)
            else it.copy(count2 = 0, countFinal = it.countFinal - it.count2)
        }
    }

    fun resetCountTotal() {
        _uiState.update { it.copy(countFinal = 0) }
    }

    fun resetCountTotalV2() {
        _uiState.update { it.copy(countFinal = 0, count1 = 0, count2 = 0) }
    }

    fun updateIncrement(isIncrement1: Boolean, newValue: String) {
        _uiState.update {
            if(isIncrement1) it.copy(increment1 = newValue)
            else it.copy(increment2 = newValue)
        }
    }
}