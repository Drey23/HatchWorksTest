package com.andreylindo.hatchworks.ui.screens.details

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.andreylindo.hatchworks.R
import com.andreylindo.hatchworks.ui.custom_composables.CustomTopAppBar
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalGlideComposeApi::class,
)
@Composable
fun DetailsScreen(navController: NavHostController, imageUrl: String) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = LocalContext.current.getString(R.string.details),
                Icons.Filled.ArrowBack,
                navIconClick = {
                    navController.popBackStack()
                }
            )
        },
    ) { innerPadding ->
        GlideImage(
            model = imageUrl,
            contentDescription = "Pokemon Card",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding),
        )
    }
}