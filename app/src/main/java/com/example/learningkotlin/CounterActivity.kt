package com.example.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CounterActivity : AppCompatActivity() {
    private var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        initButtons()
    }
    private fun initButtons() {
        findViewById<Button>(R.id.btnIncrement).setOnClickListener {
            value++;
            updateTvValue();
        };
        findViewById<Button>(R.id.btnDecrement).setOnClickListener {
            value--;
            updateTvValue();
        };
    }
    private fun updateTvValue() {
        findViewById<TextView>(R.id.tvValue).text = value.toString();
    }
}