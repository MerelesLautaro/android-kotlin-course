package com.example.bankingapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import java.math.BigDecimal

@Composable
fun TransactionDialog(
    action: TransactionAction,
    onDismiss: () -> Unit,
    onConfirm: (BigDecimal, String?) -> Unit
) {

    var amountText by remember { mutableStateOf("") }
    var targetAccount by remember { mutableStateOf("") }

    val title = when (action) {
        TransactionAction.DEPOSIT -> "Ingresar dinero"
        TransactionAction.WITHDRAW -> "Retirar dinero"
        TransactionAction.TRANSFER -> "Transferir dinero"
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },

        text = {

            Column {

                TextField(
                    value = amountText,
                    onValueChange = { amountText = it },
                    label = { Text("Monto") }
                )

                if (action == TransactionAction.TRANSFER) {

                    TextField(
                        value = targetAccount,
                        onValueChange = { targetAccount = it },
                        label = { Text("Cuenta destino") }
                    )

                }

            }

        },

        confirmButton = {

            Button(
                onClick = {

                    val amount = amountText.toBigDecimalOrNull()

                    if (amount != null) {

                        if (action == TransactionAction.TRANSFER) {

                            if (targetAccount.isNotBlank()) {
                                onConfirm(amount, targetAccount)
                            }

                        } else {

                            onConfirm(amount, null)

                        }

                    }

                }
            ) {
                Text("Confirmar")
            }

        },

        dismissButton = {

            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }

        }
    )

}