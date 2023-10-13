package com.example.learningkotlin.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R
import com.example.learningkotlin.todoapp.task.Task
import com.example.learningkotlin.todoapp.task.TaskAdapter
import com.example.learningkotlin.todoapp.taskcategory.TaskCategoryAdapter
import com.example.learningkotlin.todoapp.utils.TaskCategoryFilter
import com.example.learningkotlin.todoapp.utils.TodoAppProviderData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoAppActivity : AppCompatActivity() {
    private lateinit var rvTaskCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var taskCategoryAdapter: TaskCategoryAdapter
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var dialogCreateTask: TodoAppDialogCreateTask
    private var tasks: MutableList<Task> = TodoAppProviderData.getDefaultTasks()
    private lateinit var taskCategoryFilter: TaskCategoryFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_app)
        setup()
    }
    private fun setup() {
        taskCategoryFilter = TaskCategoryFilter {
            taskAdapter.tasks =
                if (taskCategoryFilter.isEmpty()) ArrayList(tasks)
                else tasks.filter { taskCategoryFilter.has(it.category.id) }
            updateTaskCategoryAdapter()
            updateTaskAdapter()
        }
        dialogCreateTask = TodoAppDialogCreateTask(this)
        setupRvTaskCategories()
        setupRvTasks()
        setupFabAddTask()
    }
    private fun setupRvTaskCategories() {
        rvTaskCategories = findViewById(R.id.rvTaskCategories)
        taskCategoryAdapter = TaskCategoryAdapter(TodoAppProviderData.taskCategories, taskCategoryFilter)
        rvTaskCategories.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)
        rvTaskCategories.adapter = taskCategoryAdapter
    }
    private fun setupRvTasks() {
        rvTasks = findViewById(R.id.rvTasks)
        taskAdapter = TaskAdapter(tasks) { updateTaskAdapter()  }
        rvTasks.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = taskAdapter
    }
    private fun setupFabAddTask() {
        fabAddTask = findViewById(R.id.fabAddTask)
        fabAddTask.setOnClickListener {
            dialogCreateTask.show()
        }
    }
    private fun updateTaskCategoryAdapter() {
        taskCategoryAdapter.notifyDataSetChanged()
    }
    private fun updateTaskAdapter() {
        taskAdapter.notifyDataSetChanged()
    }
    fun addTaskToList(task: Task) {
        tasks.add(task)
        updateTaskAdapter()
    }
}