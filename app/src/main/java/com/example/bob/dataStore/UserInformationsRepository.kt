package com.example.bob.dataStore

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.LocalDate

class UserInformationsRepository(
    /*private val dataStore: DataStore<Preferences>,*/
    private val context: Context
) {
    private companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_datastore")
        val USER_NAME = stringPreferencesKey("user_name")
        val LAST_PERIOD_DATE = stringPreferencesKey("last_period_date")
        const val TAG = "UserPreferencesRepo"
    }

    val getUserInformations: Flow<String> = context.dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[USER_NAME] ?: ""
            preferences[LAST_PERIOD_DATE]?: ""
        }

/*    val getLastPeriodDate: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences -> preferences[LAST_PERIOD_DATE] ?: "" }*/

    suspend fun saveUserInformations(userInformations: UserInformations) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = userInformations.userName
            preferences[LAST_PERIOD_DATE] = userInformations.lastPeriodDate.toString()
        }
    }

/*    suspend fun saveLastPeriodDate(date: LocalDate) {
        dataStore.edit { preferences ->
            preferences[LAST_PERIOD_DATE] = date.toString()
        }
    }*/
}