package es.resuacode.navigation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Secondary

@Serializable
object Tercera

@Serializable
data class Cuarta(val param: String? = null, val param2: String? = null)