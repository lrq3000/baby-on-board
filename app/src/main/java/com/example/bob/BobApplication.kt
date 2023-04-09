package com.example.bob

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.bob.dataStore.UserInformationsRepository


private const val INFORMATIONS_PREFERENCE_NAME = "user_informations"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = INFORMATIONS_PREFERENCE_NAME
)

class BobApplication : Application() {
    lateinit var userInformationsRepository: UserInformationsRepository




    override fun onCreate() {
        super.onCreate()
        userInformationsRepository = UserInformationsRepository(dataStore)

    }
}