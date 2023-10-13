package com.example.learningkotlin.todoapp.taskcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R
import com.example.learningkotlin.todoapp.utils.TaskCategoryFilter

class TaskCategoryAdapter(
    private val taskCategories: List<TaskCategory>,
    private val taskCategoryFilter: TaskCategoryFilter
): RecyclerView.Adapter<TaskCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task_category, parent, false)
        return TaskCategoryViewHolder(view, taskCategoryFilter)
    }
    override fun getItemCount(): Int = taskCategories.size
    override fun onBindViewHolder(holder: TaskCategoryViewHolder, position: Int) {
        holder.render(taskCategories[position])
    }
}