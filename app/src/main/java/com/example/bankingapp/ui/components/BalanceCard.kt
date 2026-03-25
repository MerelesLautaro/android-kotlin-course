package com.example.bankingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

@Composable
fun BalanceCard(
    balance: BigDecimal?,
    showBalance: Boolean,
    onToggleVisibility: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Balance",
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (showBalance)
                        "$ ${balance?.toPlainString() ?: "0"}"
                    else
                        "****",
                    style = MaterialTheme.typography.headlineSmall
                )

            }

            IconButton(onClick = onToggleVisibility) {

                Icon(
                    imageVector =
                        if (showBalance)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                    contentDescription = "toggle balance"
                )

            }

        }

    }

}