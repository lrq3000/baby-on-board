package com.example.bob.data

import android.content.Context

interface AppContainer {
    val informationsRepository: InformationsRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val informationsRepository: InformationsRepository by lazy {
        OfflineInformationsRepository(BobDatabase.getDatabase(context).informationsDao())
    }
}