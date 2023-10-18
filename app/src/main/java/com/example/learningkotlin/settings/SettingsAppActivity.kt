package com.example.learningkotlin.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.learningkotlin.databinding.ActivitySettingsAppBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "learning_kotlin")

class SettingsAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsAppBinding
    private lateinit var dataStoreHelper: DataStoreHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup() {
        setupUIOptions()
        dataStoreHelper = DataStoreHelper(dataStore)
        dataStoreHelper.preloadData { settingsOptions ->
            runOnUiThread {
                binding.switchDarkMode.isChecked = settingsOptions.darkMode
                binding.rsVolume.setValues(settingsOptions.volume.toFloat())
                binding.switchBluetooth.isChecked = settingsOptions.bluetooth
                binding.switchPhoneVibration.isChecked = settingsOptions.phoneVibration
            }
        }
    }
    private fun setupUIOptions() {
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked -> switchDarkMode(isChecked) }
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            dataStoreHelper.saveData(intPreferencesKey(DataStoreHelper.PREFERENCE_VOLUME_LVL), value.toInt())
        }
        binding.switchBluetooth.setOnCheckedChangeListener { _, isChecked ->
            dataStoreHelper.saveData(booleanPreferencesKey(DataStoreHelper.PREFERENCE_BLUETOOTH), isChecked)
        }
        binding.switchPhoneVibration.setOnCheckedChangeListener { _, isChecked ->
            dataStoreHelper.saveData(booleanPreferencesKey(DataStoreHelper.PREFERENCE_PHONE_VIBRATION), isChecked)
        }
    }

    private fun switchDarkMode(darkMode: Boolean) {
        DarkModeHelper.switchDarkMode(delegate, darkMode)
        dataStoreHelper.saveData(booleanPreferencesKey(DataStoreHelper.PREFERENCE_DARK_MODE), darkMode)
    }
}