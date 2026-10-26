package es.resuacode.listas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.resuacode.listas.data.FullName
import es.resuacode.listas.data.nombres

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    ListaNombres(nombres, modifier)
}


@Composable
fun ListaNombres(nombres: List<FullName>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Equipaso")
        }
        items(nombres, key = { nombre -> nombre.apellido }) { nombre ->
            Row() {
                Text(text = nombre.nombre)
                Spacer(Modifier.size(8.dp))
                Text(nombre.apellido)
            }
        }
        item {
            Text("Hasta aquí")
        }
    }
}

@Composable
fun ListaVertical(modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(100) { index ->
            Text(text = "Elemento $index")
        }
    }
}

@Composable
fun ListaHorizontal(modifier: Modifier) {
    LazyRow(
        modifier = modifier
    ) {
        items(100) { index ->
            Text(text = "Elemento $index")
        }
    }
}