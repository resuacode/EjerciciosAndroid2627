package es.resuacode.navigation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.resuacode.navigation.ui.navigation.Cuarta
import es.resuacode.navigation.ui.navigation.Home
import es.resuacode.navigation.ui.navigation.Secondary
import es.resuacode.navigation.ui.navigation.Tercera
import es.resuacode.navigation.ui.screens.cuarta.CuartaScreen
import es.resuacode.navigation.ui.screens.HomeScreen
import es.resuacode.navigation.ui.screens.SecondaryScreen
import es.resuacode.navigation.ui.screens.TerceraScreen


@Composable
fun MyApp(
    paddingValues: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onGoToSecondary = { navController.navigate(Secondary) },
                onGoToTercera = { navController.navigate(Tercera) }
            )
        }
        composable<Secondary> {
            SecondaryScreen (
                onGoToTercera = { navController.navigate(Tercera) }
            ) {
                navController.popBackStack()
            }
        }
        composable<Tercera> {
            TerceraScreen(
                onGoCuartaSimple = { navController.navigate(Cuarta()) },
                onGoCuartaParam = { param, param2 ->
                    navController.navigate(Cuarta(param = param, param2 = param2))
                                  },
                onGoBack = { navController.popBackStack() }
            )
        }
        composable<Cuarta> {
            CuartaScreen { navController.popBackStack() }
        }
    }
}