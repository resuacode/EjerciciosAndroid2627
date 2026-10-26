package es.resuacode.navigation.ui.screens.cuarta

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import es.resuacode.navigation.ui.navigation.Cuarta

data class CuartaState(
    val texto :  String? = null,
    val texto2 : String? = null
)

class CuartaViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val cuartaState = savedStateHandle.toRoute<Cuarta>()
    val texto = cuartaState.param
    val texto2 = cuartaState.param2


}