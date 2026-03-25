package com.example.bankingapp.di

import android.content.Context

object ServiceLocator {

    private var container: AppContainer? = null

    fun provideContainer(context: Context): AppContainer {

        return container ?: synchronized(this) {

            container ?: AppContainer(context.applicationContext).also {
                container = it
            }

        }

    }

}