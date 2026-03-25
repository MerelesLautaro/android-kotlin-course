package com.example.bankingapp

import android.app.Application
import com.example.bankingapp.di.ServiceLocator

class BankingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocator.provideContainer(this)
    }

}