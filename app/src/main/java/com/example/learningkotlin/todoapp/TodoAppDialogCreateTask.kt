package com.example.learningkotlin.todoapp

import android.app.Dialog
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import com.example.learningkotlin.R
import com.example.learningkotlin.todoapp.task.Task
import com.example.learningkotlin.todoapp.taskcategory.TaskCategory
import com.example.learningkotlin.todoapp.utils.TodoAppProviderData

class TodoAppDialogCreateTask {
    private lateinit var dialog: Dialog
    private lateinit var context: TodoAppActivity
    private lateinit var etTaskDescription: EditText
    private lateinit var rgTaskCategories: RadioGroup
    private var newTask: Task
    constructor(context: TodoAppActivity) {
        setupDialog(context)
        newTask = Task()
        setupRgTaskCategories()
        setupBtnCreateTask()
        setupEtTaskDescription()
    }
    private fun setupDialog(context: TodoAppActivity) {
        this.context = context
        dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_todo_create_task)
    }
    private fun setupEtTaskDescription() {
        etTaskDescription = dialog.findViewById(R.id.etTaskDescription)
    }
    private fun setupRgTaskCategories() {
        rgTaskCategories = dialog.findViewById(R.id.rgTaskCategory)
        TodoAppProviderData.taskCategories.forEach {
            val rbTaskCategory = createRbTaskCategory(it)
            rgTaskCategories.addView(rbTaskCategory)
        }
    }
    private fun createRbTaskCategory(taskCategory: TaskCategory): RadioButton {
        val rbTaskCategory =  LayoutInflater.from(dialog.context)
            .inflate(R.layout.rb_task_category, null)
            .findViewById<RadioButton>(R.id.rbTaskCategory)

        rbTaskCategory.id = View.generateViewId()
        rbTaskCategory.text = taskCategory.categoryName
        rbTaskCategory.buttonTintList = ColorStateList.valueOf(ContextCompat.getColor(context, taskCategory.color))

        rbTaskCategory.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) return@setOnCheckedChangeListener
            newTask.category = taskCategory
        }
        return rbTaskCategory
    }
    private fun setupBtnCreateTask() {
        dialog.findViewById<Button>(R.id.btnCreateTask)
            .setOnClickListener {
                addTask()
            }
    }
    private fun addTask() {
        newTask.description = etTaskDescription.text.toString()
        context.addTaskToList(newTask)
        hide()
        resetForm()
    }
    private fun resetForm() {
        newTask = Task()
        etTaskDescription.text.clear()
        rgTaskCategories.clearCheck()
    }
    fun show() {
        dialog.show()
    }
    private fun hide() {
        dialog.hide()
    }
}