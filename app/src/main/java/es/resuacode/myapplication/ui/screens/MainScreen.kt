package es.resuacode.myapplication.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.resuacode.myapplication.R
import es.resuacode.myapplication.data.nombres
import es.resuacode.myapplication.ui.theme.MyApplicationTheme
import es.resuacode.myapplication.ui.theme.Typography
import org.jetbrains.annotations.Debug
import org.w3c.dom.Text
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

//    var count1 by rememberSaveable { mutableIntStateOf(0) }
//    var count2 by rememberSaveable { mutableIntStateOf(0)}
//    var increment1 by rememberSaveable { mutableStateOf("1") }
//    var increment2 by rememberSaveable { mutableStateOf("1")}
//    var countFinal by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(48.dp))
        Text(
            stringResource(R.string.contadores),
            style = Typography.titleLarge
        )

        ContadorSimple(
            count = state.count1,
            increment = state.increment1,
            contar = {
                if (state.increment1.isBlank()){
                    Toast.makeText(context, "El incremento debe tener contenido", Toast.LENGTH_SHORT).show()
                } else if (state.increment1.isDigitsOnly()) viewModel.incrementar(true)
                else Toast.makeText(context, "El incremento solo puede contener números", Toast.LENGTH_SHORT).show()
            },
            reset = { viewModel.resetCount(true) },
            updateIncrement = { viewModel.updateIncrement(true, it) },
        )
        ContadorSimple(
            count = state.count2,
            increment = state.increment2,
            contar = {
                if (state.increment2.isBlank()){
                    Toast.makeText(context, "El incremento debe tener contenido", Toast.LENGTH_SHORT).show()
                } else if (state.increment2.isDigitsOnly()) viewModel.incrementar(false)
                else Toast.makeText(context, "El incremento solo puede contener números", Toast.LENGTH_SHORT).show()
            },
            reset = { viewModel.resetCount(false) },
            updateIncrement = { viewModel.updateIncrement(false, it) },
        )
        ContadorTotal(
            count = state.countFinal,
        ) {
            viewModel.resetCountTotal()
        }
        Spacer(Modifier.size(48.dp))
    }
}

@Composable
fun ContadorSimple(
    count: Int,
    increment: String,
    contar:() -> Unit,
    reset: () -> Unit,
    updateIncrement: (String) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Contador: $count")
            IconButton(
                onClick = reset
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = null,
                )
            }
        }
        IconButton(
            onClick = contar
        ) {
            Icon(
                painter = painterResource(R.drawable.add),
                contentDescription = null,
            )
        }
        TextField(
            value = increment,
            onValueChange = updateIncrement,
            modifier = Modifier
                .width(128.dp)
                .onFocusChanged {
                    if (it.hasFocus) updateIncrement("")
                    else if (increment.isBlank()) updateIncrement("1")
                }
            ,
            label = { Text("Incrementador")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun ContadorTotal(count: Int, reset: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Contador: $count")
        IconButton(
            onClick = reset
        ) {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = null,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(48.dp))

        Text(
            stringResource(R.string.contadores),
            style = Typography.titleLarge
        )
        ContadorSimple(0, "0", {} , {} ,{})
        ContadorSimple(0, "0", {} , {} ,{})
        ContadorTotal(0, {})
        Spacer(Modifier.size(48.dp))

    }
}
