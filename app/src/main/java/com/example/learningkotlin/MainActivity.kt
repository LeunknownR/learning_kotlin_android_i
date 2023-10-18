package com.example.learningkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learningkotlin.databinding.ActivityMainBinding
import com.example.learningkotlin.imccalculator.ImcCalculatorActivity
import com.example.learningkotlin.settings.DarkModeHelper
import com.example.learningkotlin.settings.DataStoreHelper
import com.example.learningkotlin.settings.SettingsAppActivity
import com.example.learningkotlin.settings.dataStore
import com.example.learningkotlin.superheroapp.SuperheroAppActivity
import com.example.learningkotlin.todoapp.TodoAppActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup() {
        setupRoutingApps()
        readDarkModePreference()
    }
    private fun setupRoutingApps() {
        binding.btnCounter.setOnClickListener {
            intent = Intent(this, CounterActivity::class.java)
            startActivity(intent)
        }
        binding.btnImcCalculator.setOnClickListener {
            intent = Intent(this, ImcCalculatorActivity::class.java)
            startActivity(intent)
        }
        binding.btnTodoApp.setOnClickListener {
            intent = Intent(this, TodoAppActivity::class.java)
            startActivity(intent)
        }
        binding.btnSuperHeroApp.setOnClickListener {
            intent = Intent(this, SuperheroAppActivity::class.java)
            startActivity(intent)
        }
        binding.btnSettings.setOnClickListener {
            intent = Intent(this, SettingsAppActivity::class.java)
            startActivity(intent)
        }
    }
    private fun readDarkModePreference() {
        DataStoreHelper(dataStore).preloadData { settingsOptions ->
            runOnUiThread {
                DarkModeHelper.switchDarkMode(delegate, settingsOptions.darkMode)
            }
        }
    }
}