package com.example.learningkotlin.todoapp.taskcategory

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R
import com.example.learningkotlin.todoapp.utils.TaskCategoryFilter

class TaskCategoryViewHolder: RecyclerView.ViewHolder {
    private val tvCategoryName: TextView
    private val divider: View
    private val cvTaskCategory: CardView
    private val taskCategoryFilter: TaskCategoryFilter
    constructor(view: View, taskCategoryFilter: TaskCategoryFilter) : super(view) {
        tvCategoryName = view.findViewById(R.id.tvCategoryName)
        divider = view.findViewById(R.id.divider)
        cvTaskCategory = view.findViewById(R.id.cvTaskCategory)
        this.taskCategoryFilter = taskCategoryFilter
    }
    fun render(taskCategory: TaskCategory) {
        tvCategoryName.text = taskCategory.categoryName
        divider.setBackgroundColor(ContextCompat.getColor(divider.context, taskCategory.color))
        fillCardBackgroundColor(taskCategory.id)
        itemView.setOnClickListener {
            taskCategoryFilter.toggle(taskCategory.id)
        }
    }
    private fun fillCardBackgroundColor(taskCategoryId: String) {
        val color: Int =
            if (taskCategoryFilter.has(taskCategoryId)) R.color.todo_app_background_card_selected
            else R.color.todo_app_background_card
        Log.i("manuel", "$taskCategoryId |" + taskCategoryFilter.has(taskCategoryId))
        cvTaskCategory.setCardBackgroundColor(ContextCompat.getColor(cvTaskCategory.context, color))
    }
}