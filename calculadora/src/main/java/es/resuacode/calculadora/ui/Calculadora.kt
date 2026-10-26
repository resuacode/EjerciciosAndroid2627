package es.resuacode.calculadora.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.resuacode.calculadora.ui.theme.AzulIgual
import es.resuacode.calculadora.ui.theme.ColorLetras
import es.resuacode.calculadora.ui.theme.Fondo
import es.resuacode.calculadora.ui.theme.GrisOscuro

@Composable
fun Calculadora(
    paddingValues: PaddingValues,
    viewModel: CalculadoraViewModel = viewModel()
    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayCalculadora(uiState.textDisplay, Modifier.weight(3f))
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("%") { viewModel.porcentaje(context) }
            BotonCalculadora("CE") { viewModel.borrarEntrada()}
            BotonCalculadora("C") { viewModel.borrarTodo()}
            BotonCalculadora("⌫") { viewModel.borrarDigito() }
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("⅟") { viewModel.fraccion()}
            BotonCalculadora("x²") {viewModel.cuadrado()}
            BotonCalculadora("√") { viewModel.raizCuadrada()}
            BotonCalculadora("÷") { viewModel.cambiarOperador(Operador.DIV)}
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("7", colorFondo = Fondo) {
                viewModel.displayNum("7")
            }
            BotonCalculadora("8", colorFondo = Fondo) {
                viewModel.displayNum("8")
            }
            BotonCalculadora("9", colorFondo = Fondo) {
                viewModel.displayNum("9")
            }
            BotonCalculadora("x") { viewModel.cambiarOperador(Operador.MULT)}
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("4", colorFondo = Fondo) {
                viewModel.displayNum("4")
            }
            BotonCalculadora("5", colorFondo = Fondo) {
                viewModel.displayNum("5")

            }
            BotonCalculadora("6", colorFondo = Fondo) {
                viewModel.displayNum("6")

            }
            BotonCalculadora("-") { viewModel.cambiarOperador(Operador.RESTA)}
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("1", colorFondo = Fondo) {
                viewModel.displayNum("1")
            }
            BotonCalculadora("2", colorFondo = Fondo) {
                viewModel.displayNum("2")
            }
            BotonCalculadora("3", colorFondo = Fondo) {
                viewModel.displayNum("3")
            }
            BotonCalculadora("+") { viewModel.cambiarOperador(Operador.SUMA)}
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            BotonCalculadora("±") { viewModel.cambioSigno() }
            BotonCalculadora("0", colorFondo = Fondo) {
                viewModel.displayNum("0")
            }
            BotonCalculadora(".") { viewModel.displayNum(".")}
            BotonCalculadora("=",
                colorFondo = AzulIgual) { viewModel.igual(context)}
        }
    }
}

@Composable
fun RowScope.BotonCalculadora(
    simbolo: String,
    modifier: Modifier = Modifier,
    colorLetras: Color = ColorLetras,
    colorFondo: Color = GrisOscuro,
    accion: () -> Unit,

    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .border(width = 1.dp, color = Fondo)
            .background(colorFondo)
            .clickable {
                accion()
            }
            .then(modifier)
    ) {
        Text(
            simbolo,
            fontSize = 25.sp,
            color = colorLetras
        )
    }
}

@Composable
fun DisplayCalculadora(displayText: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(20.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            text = displayText,
            textAlign = TextAlign.End,
            color = ColorLetras,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
    }
}