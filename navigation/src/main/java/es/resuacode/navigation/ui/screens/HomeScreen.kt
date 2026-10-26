package es.resuacode.navigation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onGoToSecondary: () -> Unit,
    onGoToTercera: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("Estoy en la HomeScreen")
        Button(
            onClick = onGoToSecondary
        ) {
            Text("Pantalla Secundaria")
        }
        Button(
            onClick = onGoToTercera
        ) {
            Text("Pantalla Terciaria")
        }
    }
}