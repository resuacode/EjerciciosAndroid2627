package es.resuacode.todo.ui

import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import es.resuacode.todo.R
import es.resuacode.todo.data.todoIniciales

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    viewModel: MainScreenViewModel = viewModel()
) {
    // inicializar el state y gestionar el estado
    Column(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewItemRow(
            newItem = "", // pdte de actualizar
            updateText = {
               //  updateText(it)
                         }, //pdte de implementar en vm
            add = {
              //   newItem()
            }) //pdte de implementar en vm

        LazyColumn(

        ) {
            items(todoIniciales) { // Aquí no tiene que estar
                ItemRow(
                    todo = it.tarea, // pdte de actualizar
                    cambiarCompletado = {}, //pdte de implementar en vm
                    borrarItem = {} //pdte de implementar en vm
                )
            }
        }
    }
}

@Composable
fun ItemRow(todo: String, cambiarCompletado: () -> Unit, borrarItem: () -> Unit) {
    Row() {
        Text(todo)
        //PDTE añadir botones acciones
    }
}

@Composable
fun NewItemRow(newItem: String, updateText: (String) -> Unit, add: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Nueva tarea")
        TextField(
            value = newItem,
            onValueChange = { updateText(it) }
        )
        IconButton(
            onClick = { add() }
        ) {
            Icon(painterResource(R.drawable.add_24px), contentDescription = "add icon")
        }
    }
}