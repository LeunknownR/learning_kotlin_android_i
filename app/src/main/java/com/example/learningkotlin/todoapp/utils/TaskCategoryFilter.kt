package com.example.learningkotlin.todoapp.utils

class TaskCategoryFilter(
    private val filterTasks: () -> Unit
) {
    private val taskCategories: HashSet<String> = hashSetOf()
    fun toggle(taskCategoryId: String) {
        if (has(taskCategoryId)) taskCategories.remove(taskCategoryId)
        else taskCategories.add(taskCategoryId)
        filterTasks()
    }
    fun isEmpty(): Boolean {
        return this.taskCategories.isEmpty()
    }
    fun has(taskCategoryId: String): Boolean {
        return this.taskCategories.contains(taskCategoryId)
    }
}