package es.resuacode.navigation.ui.screens.cuarta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CuartaScreen(
    viewModel: CuartaViewModel = viewModel(),
    onGoBack: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Estoy en la Cuarta Screen")
        if (!viewModel.texto.isNullOrBlank()) Text(viewModel.texto)
        else Text("No he recibido el primer parámetro")
        if (!viewModel.texto2.isNullOrBlank()) Text(viewModel.texto2)
        else Text("No he recibido el segundo parámetro")
        Button(
            onClick = onGoBack
        ) {
            Text("Volver")
        }
    }
}