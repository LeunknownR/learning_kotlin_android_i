package com.example.learningkotlin.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreHelper(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val PREFERENCE_DARK_MODE = "dark_mode"
        const val PREFERENCE_BLUETOOTH = "bluetooth"
        const val PREFERENCE_VOLUME_LVL = "volume_lvl"
        const val PREFERENCE_PHONE_VIBRATION = "phone_vibration"
    }
    private var firstDataStoreReading: Boolean = true
    fun preloadData(handler: (SettingsOptions) -> Unit) {
        val dataStoreMapFlow = dataStore.data
            .filter { firstDataStoreReading }
            .map { preferences ->
                SettingsOptions(
                    darkMode = preferences[booleanPreferencesKey(PREFERENCE_DARK_MODE)] ?: false,
                    bluetooth = preferences[booleanPreferencesKey(PREFERENCE_BLUETOOTH)] ?: false,
                    volume = preferences[intPreferencesKey(PREFERENCE_VOLUME_LVL)] ?: 50,
                    phoneVibration = preferences[booleanPreferencesKey(PREFERENCE_PHONE_VIBRATION)] ?: false,
                )
            }
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreMapFlow.collect { settingOptions ->
                firstDataStoreReading = false
                CoroutineScope(Dispatchers.IO).launch {
                    handler(settingOptions)
                }
            }
        }
    }
    fun <T> saveData(preferenceKey: Preferences.Key<T>, value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { preferences ->
                preferences[preferenceKey] = value
            }
        }
    }
}