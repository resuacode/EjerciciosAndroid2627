package es.resuacode.navigation.ui.screens

import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TerceraScreen(
    onGoCuartaSimple: () -> Unit,
    onGoCuartaParam: (String, String) -> Unit,
    onGoBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Estoy en la Tercera Screen")
        Button(
            onClick = onGoCuartaSimple
        ) {
            Text("CUARTA SIN PARAM")
        }
        var texto by rememberSaveable() { mutableStateOf("") }

        TextField(
            value = texto,
            onValueChange = { texto = it }
        )
        var texto2 by rememberSaveable() { mutableStateOf("") }

        TextField(
            value = texto2,
            onValueChange = { texto2 = it }
        )
        Button(
            onClick = {
                onGoCuartaParam(texto, texto2)
            }
        ) {
            Text("CUARTA CON PARAM")
        }
        Button(
            onClick = onGoBack
        ) {
            Text("Volver")
        }
    }
}