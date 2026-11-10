package es.resuacode.todo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import es.resuacode.todo.R
import es.resuacode.todo.ui.navigation.Add
import es.resuacode.todo.ui.navigation.Main
import es.resuacode.todo.ui.screens.add.AddScreen
import es.resuacode.todo.ui.screens.main.MainScreen
import es.resuacode.todo.ui.utils.TodoTopAppBar

@Composable
fun MyApp() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Main) {
        composable<Main> {
            val savedStateHandle = it.savedStateHandle
            val nuevaTarea = savedStateHandle.get<String>("TODO-ITEM")

            MainScreen(
                nuevaTarea = nuevaTarea,
                tareaConsumida = {
                    savedStateHandle.remove<String>("TODO-ITEM") // Limpio el stateHandle
                },
                onGoToAdd = { navController.navigate(Add) }
            )
        }

        composable<Add> {
            AddScreen(
                onGoBack = { navController.popBackStack() },
                onAddAndGoBack = { tarea ->
                    navController.previousBackStackEntry?.savedStateHandle?.set("TODO-ITEM", tarea)
                    navController.popBackStack()
                }
            )
        }
    }


}