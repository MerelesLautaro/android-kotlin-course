package com.example.bankingapp.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bankingapp.data.remote.dto.TransactionDetailDto
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionItem(
    transaction: TransactionDetailDto
) {

    val icon = when (transaction.transactionType) {
        "CASH_DEPOSIT" -> Icons.Default.ArrowDownward
        "CASH_WITHDRAWAL" -> Icons.Default.ArrowUpward
        else -> Icons.Default.SwapHoriz
    }

    val title = when (transaction.transactionType) {
        "CASH_DEPOSIT" -> "Deposit"
        "CASH_WITHDRAWAL" -> "Withdrawal"
        else -> "Transfer"
    }

    val amountColor = when (transaction.transactionType) {
        "CASH_DEPOSIT" -> Color(0xFF2E7D32) // verde
        else -> Color(0xFFC62828) // rojo
    }

    val date = remember(transaction.transactionDate) {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        sdf.format(Date(transaction.transactionDate))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "$ ${transaction.amount}",
                    color = amountColor,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = transaction.transactionStatus,
                    style = MaterialTheme.typography.labelSmall,
                    color = when (transaction.transactionStatus) {
                        "APPROVED" -> Color(0xFF2E7D32)
                        "PENDING" -> Color(0xFFF9A825)
                        else -> Color.Red
                    }
                )

            }

        }

    }

}