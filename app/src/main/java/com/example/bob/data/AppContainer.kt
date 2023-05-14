package com.example.bob.data

import android.content.Context

interface AppContainer {
    val notesRepository: NotesRepository
    val contactionsRepository: ContactionsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(BobDatabase.getDatabase(context).notesDao())
    }
    override val contactionsRepository: ContactionsRepository by lazy {
        OfflineContractionsRepository(BobDatabase.getDatabase(context).contractionDao())
    }
}