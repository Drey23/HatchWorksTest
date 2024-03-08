package com.andreylindo.hatchworks.navigation

sealed class RouteScreens(val route: String) {
    object Home : RouteScreens(route = "home_screen")

    data class Details(val imageUrl: String? = null) :
        RouteScreens(route = imageUrl?.let { "details_screen?imageUrl=$imageUrl" } ?: "details_screen")
}