package com.example.learningkotlin.todoapp.task

import com.example.learningkotlin.todoapp.taskcategory.TaskCategory

class Task {
    companion object {
        var TASK_IDENTITY: Int = 0
    }
    constructor(description: String, category: TaskCategory, done: Boolean) {
        id = ++TASK_IDENTITY
        this.description = description
        this.category = category
        this.done = done
    }
    constructor() {
        id = ++TASK_IDENTITY
        description = null
        category = null
        done = false
    }
    var id: Int
        private set
    var description: String?
    var category: TaskCategory?
    var done: Boolean
    fun isValid(): Boolean {
        return description.orEmpty().isNotEmpty() && category != null
    }
}