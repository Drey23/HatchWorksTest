package com.andreylindo.hatchworks.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andreylindo.hatchworks.common.EMPTY_STRING
import com.andreylindo.hatchworks.ui.screens.details.DetailsScreen
import com.andreylindo.hatchworks.ui.screens.home.HomeScreen
import com.andreylindo.hatchworks.ui.screens.home.HomeViewModel

const val IMAGE_URL_KEY = "imageUrl"

@Composable
fun NavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {
    val durationMillis = 250

    NavHost(
        navController = navController,
        startDestination = RouteScreens.Home.route,
    )
    {
        composable(route = RouteScreens.Home.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(
            route = RouteScreens.Details.route + "?imageUrl={$IMAGE_URL_KEY}",
            arguments = listOf(
                navArgument(IMAGE_URL_KEY) {
                    type = NavType.StringType
                    defaultValue = EMPTY_STRING
                },
            ),
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(durationMillis)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(durationMillis)
                )
            },
        ) {
            val imageUrl = it.arguments?.getString(IMAGE_URL_KEY).orEmpty()
            DetailsScreen(navController, imageUrl = imageUrl)
        }
    }
}