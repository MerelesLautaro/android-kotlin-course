package com.example.bankingapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bankingapp.navigation.NavGraph
import com.example.bankingapp.ui.theme.BankingAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BankingAppTheme {
                NavGraph()
            }
        }
    }
}

