package com.example.learningkotlin.settings

data class SettingsOptions(
    val darkMode: Boolean,
    val bluetooth: Boolean,
    val volume: Int,
    val phoneVibration: Boolean
)
