package com.example.bankingapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.material3.MaterialTheme

@Composable
fun HomeHeaderBackground(
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier) {

        val width = size.width
        val height = size.height

        val path = Path().apply {

            moveTo(0f, height * 0.6f)

            quadraticTo(
                width * 0.25f,
                height * 0.8f,
                width * 0.5f,
                height * 0.6f
            )

            quadraticTo(
                width * 0.75f,
                height * 0.4f,
                width,
                height * 0.6f
            )

            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = path,
            color = color
        )
    }
}