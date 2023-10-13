package com.example.learningkotlin.todoapp.taskcategory

import com.example.learningkotlin.R

enum class TaskCategory(val id: String, val categoryName: String, val color: Int) {
    TASK_BUSINESS_CATEGORY("BNS", "Negocios", R.color.todo_app_business_category),
    TASK_PERSONAL_CATEGORY("PRS", "Personal", R.color.todo_app_personal_category),
    TASK_OTHER_CATEGORY("OTH", "Otros", R.color.todo_app_other_category)
}