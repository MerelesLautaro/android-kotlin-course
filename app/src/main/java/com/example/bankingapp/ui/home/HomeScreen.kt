package com.example.bankingapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bankingapp.di.ServiceLocator
import com.example.bankingapp.ui.components.ActionButtons
import com.example.bankingapp.ui.components.BalanceCard
import com.example.bankingapp.ui.components.HomeHeaderBackground
import com.example.bankingapp.ui.components.TransactionList
import com.example.bankingapp.ui.components.TransactionAction
import com.example.bankingapp.ui.components.TransactionDialog

@Composable
fun HomeScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val container = ServiceLocator.provideContainer(context)

    var currentAction by remember { mutableStateOf<TransactionAction?>(null) }

    val viewModel: HomeViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return HomeViewModel(
                    container.authRepository,
                    container.dashboardRepository,
                    container.accountRepository,
                    container.tokenManager
                ) as T

            }

        }
    )

    val state = viewModel.state

    var menuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        HomeHeaderBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Hola, ${state.name}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Box {

                IconButton(
                    onClick = { menuExpanded = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "user menu",
                        tint = Color.White
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {

                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {

                            menuExpanded = false

                            viewModel.logout {

                                navController.navigate("login") {
                                    popUpTo("home") { inclusive = true }
                                }

                            }

                        }
                    )

                }

            }

        }

        if (state.isLoading) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 140.dp)
                .padding(horizontal = 20.dp)
        ) {

            BalanceCard(
                balance = state.balance,
                showBalance = state.showBalance,
                onToggleVisibility = {
                    viewModel.toggleBalanceVisibility()
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            ActionButtons(

                onDepositClick = {
                    currentAction = TransactionAction.DEPOSIT
                },

                onWithdrawClick = {
                    currentAction = TransactionAction.WITHDRAW
                },

                onTransferClick = {
                    currentAction = TransactionAction.TRANSFER
                }

            )

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryTabRow(
                selectedTabIndex = state.selectedTab
            ) {

                Tab(
                    selected = state.selectedTab == 0,
                    onClick = { viewModel.selectTab(0) },
                    text = { Text("Main") }
                )

                Tab(
                    selected = state.selectedTab == 1,
                    onClick = { viewModel.selectTab(1) },
                    text = { Text("Invest") }
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            TransactionList(
                transactions = state.transactions
            )

        }
        currentAction?.let { action ->

            TransactionDialog(

                action = action,

                onDismiss = {
                    currentAction = null
                },

                onConfirm = { amount, account ->

                    when (action) {

                        TransactionAction.DEPOSIT ->
                            viewModel.deposit(amount)

                        TransactionAction.WITHDRAW ->
                            viewModel.withdraw(amount)

                        TransactionAction.TRANSFER ->
                            viewModel.transfer(amount, account!!)

                    }

                    currentAction = null

                }

            )

        }

    }

}