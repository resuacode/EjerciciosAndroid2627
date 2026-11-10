package es.resuacode.todo.ui.screens.main

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.resuacode.todo.R
import es.resuacode.todo.ui.utils.TodoTopAppBar


@Composable
fun MainScreen(
    nuevaTarea: String?,
    tareaConsumida: () -> Unit,
    onGoToAdd: () -> Unit
) {
    Scaffold(
        topBar = { TodoTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onGoToAdd
            ) {
                Icon(
                    painterResource(R.drawable.add_24px),
                    contentDescription = "add icon"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MainScreenContent(
            nuevaTarea = nuevaTarea,
            tareaConsumida = tareaConsumida,
            paddingValues = innerPadding,
        )
    }
}

@Composable
fun MainScreenContent(
    nuevaTarea: String?,
    tareaConsumida: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: MainScreenViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current


    // Reaccionar en caso de que llegue una nueva tarea
    LaunchedEffect(nuevaTarea) {
        if (!nuevaTarea.isNullOrBlank()) {
            viewModel.addItem(nuevaTarea)
            tareaConsumida()
        }
    }

    Column(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de tareas",
            style = MaterialTheme.typography.titleLarge
        )
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            todo,
            modifier = Modifier
                .padding(4.dp)
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

