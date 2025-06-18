package com.example.smarttasks.data.model

import java.util.Date

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: TaskCategory = TaskCategory.PERSONAL,
    val status: TaskStatus = TaskStatus.PENDING,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: Date? = null,
    val isCompleted: Boolean = false,
    val subtasks: List<Subtask> = emptyList(),
    val attachments: List<Attachment> = emptyList()
)

enum class TaskCategory(val displayName: String) {
    WORK("Work"),
    HEALTH("Health"),
    FITNESS("Fitness"),
    PERSONAL("Personal");

    companion object {
        fun fromString(value: String?): TaskCategory =
            values().find { it.name.equals(value, ignoreCase = true) } ?: PERSONAL
    }
}

data class Subtask(
    val id: String = "",
    val title: String = "",
    val isCompleted: Boolean = false
)

data class Attachment(
    val id: String = "",
    val fileName: String = "",
    val fileUrl: String = ""
)


enum class TaskStatus(val displayName: String) {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed")
}

enum class TaskPriority(val displayName: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High")
}

data class TaskApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)


