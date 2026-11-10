package es.resuacode.todo.ui.screens.add

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
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.resuacode.todo.R
import es.resuacode.todo.ui.utils.TodoTopAppBar

@Composable
fun AddScreen(onGoBack: () -> Unit,
              onAddAndGoBack: (String) -> Unit,
) {
    Scaffold(
        topBar = { TodoTopAppBar(true, onGoBack) },
    ) {
        AddScreenContent(it, onAddAndGoBack)
    }
}

@Composable
fun AddScreenContent(
    paddingValues: PaddingValues,
    onAddAndGoBack: (String) -> Unit,
    viewModel: AddViewModel = viewModel()
    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NewItemRow(
            newItem = uiState.newItemText,
            updateText = {
                viewModel.updateNewItemText(it)
            },
            add = {
                onAddAndGoBack(uiState.newItemText)
            })
    }
}

@Composable
fun NewItemRow(newItem: String, updateText: (String) -> Unit, add: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nueva tarea")
        Spacer(Modifier.size(16.dp))
        TextField(
            value = newItem,
            modifier = Modifier.width(256.dp),
            onValueChange = { updateText(it) }
        )
        Button(
            onClick = { add() },
            enabled = if (newItem.isBlank()) false else true

        ) {
            Text("Añadir Tarea")
        }
    }
}