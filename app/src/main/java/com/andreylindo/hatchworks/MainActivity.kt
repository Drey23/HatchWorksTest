package com.andreylindo.hatchworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.andreylindo.hatchworks.navigation.NavGraph
import com.andreylindo.hatchworks.ui.screens.home.HomeViewModel
import com.andreylindo.hatchworks.ui.theme.HatchWorksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            HatchWorksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val homeNavController = rememberNavController()

                    NavGraph(navController = homeNavController, homeViewModel = homeViewModel)
                }
            }
        }
    }
}