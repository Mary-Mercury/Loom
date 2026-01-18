package com.example.loom.Data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name="settings")

@Singleton
class DataStoreUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val DARK_THEME = booleanPreferencesKey("dark_theme")
    }

    val darkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[DARK_THEME] ?: false
        }

    suspend fun setDarkTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME] = value
        }
    }
}