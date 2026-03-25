package com.example.bankingapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun AuthBackground() {

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        val width = size.width
        val height = size.height

        // TOP WAVE
        val topPath = Path().apply {

            moveTo(0f, height * 0.2f)

            quadraticTo(
                width * 0.25f,
                height * 0.3f,
                width * 0.5f,
                height * 0.2f
            )

            quadraticTo(
                width * 0.75f,
                height * 0.1f,
                width,
                height * 0.2f
            )

            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = topPath,
            color = Color(0xFF1976D2)
        )

        // BOTTOM WAVE
        val bottomPath = Path().apply {

            moveTo(0f, height * 0.8f)

            quadraticTo(
                width * 0.25f,
                height * 0.7f,
                width * 0.5f,
                height * 0.8f
            )

            quadraticTo(
                width * 0.75f,
                height * 0.9f,
                width,
                height * 0.8f
            )

            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawPath(
            path = bottomPath,
            color = Color(0xFF1976D2)
        )
    }
}