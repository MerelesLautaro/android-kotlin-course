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

        val path = Path().apply {

            moveTo(0f, height * 0.3f)

            quadraticBezierTo(
                width * 0.25f,
                height * 0.4f,
                width * 0.5f,
                height * 0.3f
            )

            quadraticBezierTo(
                width * 0.75f,
                height * 0.2f,
                width,
                height * 0.3f
            )

            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = path,
            color = Color(0xFF1976D2)
        )

    }

}