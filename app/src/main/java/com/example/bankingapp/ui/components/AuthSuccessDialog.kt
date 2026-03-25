package com.example.bankingapp.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthSuccessDialog(
    message: String,
    onDismiss: () -> Unit
) {

    Dialog(onDismissRequest = { }) {

        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(280.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Success",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = onDismiss
                ) {
                    Text("OK")
                }

            }

        }

    }

}