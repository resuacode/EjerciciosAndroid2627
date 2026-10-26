package es.resuacode.calculadora.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.sqrt

enum class Operador {
    SUMA, RESTA, MULT, DIV
}

data class CalculadoraState(
    val memoria: Double = 0.0,
    val textDisplay: String = "0",
    val operador: Operador? = null
)

class CalculadoraViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CalculadoraState())
    val uiState: StateFlow<CalculadoraState> = _uiState.asStateFlow()

    fun displayNum(num: String) {
        if (num == ".")  {
            _uiState.update {
                it.copy(
                    textDisplay = it.textDisplay + num
                )
            }
        } else {
            if (_uiState.value.textDisplay == "0" && num != "0") {
                _uiState.update {
                    it.copy(
                        textDisplay = num
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        textDisplay = it.textDisplay + num
                    )
                }
            }
        }
    }

    fun cambiarOperador(newOperador: Operador) {
        _uiState.update { it.copy(
            operador = newOperador,
            memoria = it.textDisplay.toDouble(),
            textDisplay = "0"
        )
        }
    }

    fun borrarEntrada() {
        _uiState.update { it.copy(textDisplay = "0") }
    }

    fun borrarTodo() {
        _uiState.update {
            it.copy(memoria = 0.0, textDisplay = "0", operador = null)
        }
    }

    fun borrarDigito() {
        if (_uiState.value.textDisplay.length == 1) {
            borrarEntrada()
        } else {
            _uiState.update {
                it.copy(
                    textDisplay = it.textDisplay.dropLast(1)
                )
            }
        }
    }

    fun fraccion() {
        _uiState.update {
            it.copy(textDisplay = (1/it.textDisplay.toDouble()).toString())
        }
    }

    fun cuadrado() {
        _uiState.update {
            it.copy(textDisplay = (it.textDisplay.toDouble() * it.textDisplay.toDouble()).toString())
        }
    }

    fun raizCuadrada() {
        _uiState.update {
            it.copy(textDisplay = sqrt(it.textDisplay.toDouble()).toString())
        }
    }

    fun cambioSigno() {
        _uiState.update {
            it.copy(
                textDisplay = (it.textDisplay.toDouble() * -1).toString()
            )
        }
    }

    fun porcentaje(context : Context) {
        if(_uiState.value.operador != Operador.MULT) {
            Toast.makeText(context,
                "Para usar este operador debes efectuar una multiplicación",
                Toast.LENGTH_SHORT)
                .show()
        } else {
            _uiState.update {
                it.copy(
                    textDisplay = (it.memoria * it.textDisplay.toDouble() / 100).toString()
                )
            }
        }
    }

    fun igual(context: Context) {
        when(_uiState.value.operador) {
            Operador.SUMA -> {
                _uiState.update {
                    it.copy(textDisplay = (it.memoria + it.textDisplay.toDouble()).toString())
                }
            }
            Operador.RESTA -> {
                _uiState.update {
                    it.copy(textDisplay = (it.memoria - it.textDisplay.toDouble()).toString())
                }
            }
            Operador.MULT -> {
                _uiState.update {
                    it.copy(textDisplay = (it.memoria * it.textDisplay.toDouble()).toString())
                }
            }
            Operador.DIV -> {
                if (_uiState.value.textDisplay.toDouble() == 0.0) {
                    Toast.makeText(context,
                        "No se puede dividir entre 0",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    _uiState.update {
                        it.copy(textDisplay = (it.memoria / it.textDisplay.toDouble()).toString())
                    }
                }
            }
            else -> {

            }
        }
    }
}