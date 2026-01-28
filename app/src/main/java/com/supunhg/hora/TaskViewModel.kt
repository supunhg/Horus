package com.supunhg.hora

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    var tasks = mutableStateListOf<Task>() // Start with an empty task list

    fun toggleTask(taskId: Int) {
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            val task = tasks[index]
            tasks[index] = task.copy(
                status = if (task.status == TaskStatus.PENDING) TaskStatus.DONE else TaskStatus.PENDING
            )
        }
    }

    fun dropTask(taskId: Int) {
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            tasks[index] = tasks[index].copy(status = TaskStatus.DROPPED)
        }
    }

    fun deleteTask(taskId: Int) {
        tasks.removeAll { it.id == taskId }
    }

    fun addTask(title: String) {
        val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
        tasks.add(Task(newId, title, TaskStatus.PENDING))
    }
}

