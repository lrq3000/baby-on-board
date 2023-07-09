package com.baldo.bob.dataStore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserInformationsRepository(
    private val dataStore: DataStore<Preferences>,
) {
    private companion object {
        val USER_NAME = stringPreferencesKey("user_name")
        val LAST_PERIOD_DATE = longPreferencesKey("last_period_date")
        val LAST_OVULATION_DATE = longPreferencesKey("last_ovulation_date")
        const val TAG = "UserPreferencesRepo"
    }

//    val getUserInformations: Flow<String> = context.dataStore.data
//        .catch {
//            if (it is IOException) {
//                Log.e(TAG, "Error reading preferences.", it)
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }
//        .map { preferences ->
//            preferences[USER_NAME] ?: ""
//            preferences[LAST_PERIOD_DATE]?: ""
//        }

    val userPreferencesFlow: Flow<UserInformations> = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading preferences.", it)
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        val userName = preferences[USER_NAME] ?: ""
        val lastPeriodDate = preferences[LAST_PERIOD_DATE] ?: 0
        val lastOvulationDate = preferences[LAST_OVULATION_DATE]

        UserInformations(userName, lastPeriodDate, lastOvulationDate)
    }

    suspend fun saveUserInformations(userInformations: UserInformations) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userInformations.userName
            preferences[LAST_PERIOD_DATE] = userInformations.lastPeriodDate
            preferences[LAST_OVULATION_DATE] = userInformations.lastOvulationDate ?: 0
        }
    }

    fun getDetails() = dataStore.data.map {
        UserInformations(
            userName = it[USER_NAME] ?: "",
            lastPeriodDate = it[LAST_PERIOD_DATE] ?: 0,
            lastOvulationDate = it[LAST_OVULATION_DATE]
        )
    }
}