package com.example.learningkotlin.todoapp.task

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R

class TaskViewHolder: RecyclerView.ViewHolder {
    private val tvTaskDescription: TextView
    private val cbTaskDone: CheckBox
    private val updateTaskAdapter: () -> Unit
    private lateinit var task: Task
    constructor(view: View, updateTaskAdapter: () -> Unit): super(view) {
        tvTaskDescription = view.findViewById(R.id.tvTaskDescription)
        cbTaskDone = view.findViewById(R.id.cbTaskDone)
        this.updateTaskAdapter = updateTaskAdapter
    }
    fun render(task: Task) {
        this.task = task
        initToggleTaskDone()
        setupCbTaskDescription()
        setupCbTaskDone()
    }
    private fun initToggleTaskDone() {
        itemView.setOnClickListener {
            updateTaskAdapter()
            task.done = !task.done
        }
    }
    private fun setupCbTaskDescription() {
        tvTaskDescription.text = task.description
        setStyleTextOfTaskDescriptionByTaskDone()
    }
    private fun setupCbTaskDone() {
        cbTaskDone.isChecked = task.done
        cbTaskDone.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(cbTaskDone.context, task.category!!.color))
        cbTaskDone.setOnCheckedChangeListener { _, isChecked ->
            task.done = isChecked
            setStyleTextOfTaskDescriptionByTaskDone()
        }
    }
    private fun setStyleTextOfTaskDescriptionByTaskDone() {
        tvTaskDescription.paintFlags =
            if (task.done) tvTaskDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else tvTaskDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}