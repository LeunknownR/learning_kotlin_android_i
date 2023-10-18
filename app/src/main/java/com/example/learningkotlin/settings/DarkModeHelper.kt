package com.example.learningkotlin.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey

class DarkModeHelper {
    companion object {
        fun switchDarkMode(delegate: AppCompatDelegate, darkMode: Boolean) {
            AppCompatDelegate.setDefaultNightMode(
                if (darkMode) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            delegate.applyDayNight()
        }
    }
}