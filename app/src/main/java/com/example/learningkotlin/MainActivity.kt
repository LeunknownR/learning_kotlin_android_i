package com.example.learningkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.learningkotlin.imccalculator.ImcCalculatorActivity
import com.example.learningkotlin.todoapp.TodoAppActivity

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initExcerciseButtons()
    }
    private fun initExcerciseButtons() {
        findViewById<Button>(R.id.btnCounter).setOnClickListener {
            intent = Intent(this, CounterActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnImcCalculator).setOnClickListener {
            intent = Intent(this, ImcCalculatorActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnTodoApp).setOnClickListener {
            intent = Intent(this, TodoAppActivity::class.java)
            startActivity(intent)
        }
    }
}