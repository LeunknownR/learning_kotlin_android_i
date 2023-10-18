package com.example.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.learningkotlin.databinding.ActivityCounterBinding
import com.example.learningkotlin.databinding.ActivityMainBinding

class CounterActivity : AppCompatActivity() {
    private var value = 0
    private lateinit var binding: ActivityCounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }
    private fun initButtons() {
        binding.btnIncrement.setOnClickListener {
            value++;
            updateTvValue();
        };
        binding.btnDecrement.setOnClickListener {
            value--;
            updateTvValue();
        };
    }
    private fun updateTvValue() {
        binding.tvValue.text = value.toString();
    }
}