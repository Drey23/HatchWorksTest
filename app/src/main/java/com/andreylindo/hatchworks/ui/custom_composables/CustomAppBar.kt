package com.andreylindo.hatchworks.ui.custom_composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.andreylindo.hatchworks.common.EMPTY_STRING

/**
 * Copyright © 2024 HatchWorks. All rights reserved.
 *
 *
 * @author Andrey Lindo
 * @since 3/6/24
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String = EMPTY_STRING,
    navigationIcon: ImageVector? = null,
    navIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        navigationIconContentColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ),
        title = {
            Text(title)
        },
        navigationIcon = {
            navigationIcon?.let { imageVector ->
                IconButton({
                    navIconClick?.invoke()
                }) {
                    Icon(imageVector, "Back Icon")
                }
            }
        }
    )
}