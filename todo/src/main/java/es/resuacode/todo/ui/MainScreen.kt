package es.resuacode.todo.ui

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.resuacode.todo.R
import es.resuacode.todo.data.TodoItem
import es.resuacode.todo.data.todoIniciales
import es.resuacode.todo.ui.theme.MyApplicationTheme

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    viewModel: MainScreenViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NewItemRow(
            newItem = uiState.newItemText,
            updateText = {
               viewModel.updateNewItemText(it)
                         },
            add = {
               viewModel.addItem(context)
            })
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(uiState.todoList) { item ->
                ItemRow(
                    todo = item.tarea,
                    checked = item.completado,
                    cambiarCompletado = {
                        viewModel.cambiarCompletado(item = item)
                    },
                    borrarItem = {
                        viewModel.detele(item)
                    }
                )
            }
        }
    }
}

@Composable
fun ItemRow(
    todo: String,
    checked: Boolean,
    cambiarCompletado: (Boolean) -> Unit,
    borrarItem: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(todo,
            modifier = Modifier.padding(4.dp)
                .weight(0.7f),
            color = MaterialTheme.colorScheme.onTertiary,
            textDecoration =
                if (checked) TextDecoration.LineThrough
                else TextDecoration.None,

        )
        Row(
            modifier = Modifier.weight(0.3f)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = cambiarCompletado
            )
            IconButton(
                onClick = { borrarItem() }
            ) {
                Icon(painterResource(R.drawable.delete), contentDescription = "add icon")
            }
            //PDTE añadir botones acciones
        }
    }
}

@Composable
fun NewItemRow(newItem: String, updateText: (String) -> Unit, add: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Nueva tarea")
        Spacer(Modifier.size(16.dp))
        TextField(
            value = newItem,
            modifier = Modifier.width(256.dp),
            onValueChange = { updateText(it) }
        )
        IconButton(
            onClick = { add() }
        ) {
            Icon(painterResource(R.drawable.add_24px), contentDescription = "add icon")
        }
    }
}