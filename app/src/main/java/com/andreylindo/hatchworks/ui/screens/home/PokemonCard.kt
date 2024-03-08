package com.andreylindo.hatchworks.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/5/24
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonCard(url: String, name: String, onTapped: () -> Unit) {
    GlideImage(
        model = url,
        contentDescription = "Pokemon $name",
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { onTapped.invoke() },
    )
}