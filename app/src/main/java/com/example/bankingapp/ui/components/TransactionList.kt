package com.example.bankingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bankingapp.data.remote.dto.TransactionDetailDto

@Composable
fun TransactionList(
    transactions: List<TransactionDetailDto>
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(transactions) { transaction ->

            TransactionItem(transaction)

        }

    }

}