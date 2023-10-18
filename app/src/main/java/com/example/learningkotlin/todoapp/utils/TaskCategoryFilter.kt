package com.example.learningkotlin.todoapp.utils

import com.example.learningkotlin.todoapp.task.Task

class TaskCategoryFilter(
    private val tasks: List<Task>,
    private val applyFilterAndRefresh: () -> Unit
) {
    private val taskCategories: HashSet<String> = hashSetOf()
    fun toggle(taskCategoryId: String) {
        if (has(taskCategoryId)) taskCategories.remove(taskCategoryId)
        else taskCategories.add(taskCategoryId)
        applyFilterAndRefresh()
    }
    private fun isEmpty(): Boolean {
        return this.taskCategories.isEmpty()
    }
    fun filter(): List<Task> {
        return if (isEmpty()) tasks
        else tasks.filter { has(it.category!!.id) }
    }
    fun has(taskCategoryId: String): Boolean {
        return this.taskCategories.contains(taskCategoryId)
    }
}