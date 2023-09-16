package com.baldo.bob.data

import android.content.Context

interface AppContainer {
    val notesRepository: NotesRepository
    val contactionsRepository: ContactionsRepository
    val weightRepository: WeightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(BobDatabase.getDatabase(context).notesDao())
    }
    override val contactionsRepository: ContactionsRepository by lazy {
        OfflineContractionsRepository(BobDatabase.getDatabase(context).contractionDao())
    }
    override val weightRepository: WeightRepository by lazy {
        OfflineWeightRepository(BobDatabase.getDatabase(context).weightDao())
    }
}