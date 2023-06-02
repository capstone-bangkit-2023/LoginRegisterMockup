package com.example.loginregistermockup.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN_KEY = stringPreferencesKey("token")

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[TOKEN_KEY] ?: ""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit {
            it[TOKEN_KEY] = token
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TokenPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): TokenPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}