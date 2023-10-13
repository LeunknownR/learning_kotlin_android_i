package com.example.learningkotlin.todoapp.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R

class TaskAdapter(
    var tasks: List<Task>,
    private val updateTaskAdapter: () -> Unit
): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo_task, parent, false)
        return TaskViewHolder(view, updateTaskAdapter)
    }
    override fun getItemCount(): Int = tasks.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
    }
}