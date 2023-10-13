package com.example.learningkotlin.todoapp.utils

import com.example.learningkotlin.todoapp.task.Task
import com.example.learningkotlin.todoapp.taskcategory.TaskCategory

class TodoAppProviderData {
    companion object {
        val taskCategories: List<TaskCategory> = listOf(
            TaskCategory.TASK_BUSINESS_CATEGORY,
            TaskCategory.TASK_PERSONAL_CATEGORY,
            TaskCategory.TASK_OTHER_CATEGORY
        );
        fun getDefaultTasks(): MutableList<Task> {
            return mutableListOf(
                Task("Bañarme", TaskCategory.TASK_PERSONAL_CATEGORY, true),
                Task("Aprender Kotlin", TaskCategory.TASK_BUSINESS_CATEGORY, true),
                Task("Aprender Spring Boot", TaskCategory.TASK_BUSINESS_CATEGORY, false),
                Task("Aprender a tocar One Armed Scissor", TaskCategory.TASK_OTHER_CATEGORY, false),
            )
        }
    }
}