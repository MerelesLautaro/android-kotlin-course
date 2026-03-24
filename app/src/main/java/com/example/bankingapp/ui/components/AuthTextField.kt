package com.example.bankingapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },

        visualTransformation =
            if (isPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,

        modifier = Modifier.fillMaxWidth(),

        colors = TextFieldDefaults.colors(

            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),

            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary

        )

    )

}