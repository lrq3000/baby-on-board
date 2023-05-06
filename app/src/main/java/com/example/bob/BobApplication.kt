package com.example.bob

import android.app.Application
import android.content.Context
import android.util.DumpableContainer
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.bob.data.AppContainer
import com.example.bob.data.NotesRepository
import com.example.bob.dataStore.UserInformationsRepository


private const val INFORMATIONS_PREFERENCE_NAME = "user_informations"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = INFORMATIONS_PREFERENCE_NAME
)

class BobApplication : Application() {
    lateinit var userInformationsRepository: UserInformationsRepository
    lateinit var notesRepository: NotesRepository
    override fun onCreate() {
        super.onCreate()
        userInformationsRepository = UserInformationsRepository(dataStore)
        notesRepository = NotesRepository()
    }
}