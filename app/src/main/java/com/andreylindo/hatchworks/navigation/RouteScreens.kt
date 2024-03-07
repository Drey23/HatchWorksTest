package com.andreylindo.hatchworks.navigation

sealed class RouteScreens(val route: String) {
    object Home: RouteScreens("home_screen")
    object Details: RouteScreens("details_screen")
}