package com.example.smarttasks.data.model

import com.google.gson.*
import java.lang.reflect.Type

class TaskCategoryAdapter : JsonDeserializer<TaskCategory> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TaskCategory {
        return try {
            when (json.asString.trim().lowercase()) {
                "work" -> TaskCategory.WORK
                "fitness" -> TaskCategory.FITNESS
                "personal" -> TaskCategory.PERSONAL
                "health" -> TaskCategory.HEALTH
                else -> TaskCategory.WORK
            }
        } catch (e: Exception) {
            TaskCategory.WORK
        }
    }
}


class TaskPriorityAdapter : JsonDeserializer<TaskPriority> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TaskPriority {
        return try {
            when (json.asString.trim().lowercase()) {
                "low" -> TaskPriority.LOW
                "medium" -> TaskPriority.MEDIUM
                "high" -> TaskPriority.HIGH
                else -> TaskPriority.MEDIUM
            }
        } catch (e: Exception) {
            TaskPriority.MEDIUM
        }
    }
}


class TaskStatusAdapter : JsonDeserializer<TaskStatus> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TaskStatus {
        return try {
            when (json.asString.trim().lowercase()) {
                "pending" -> TaskStatus.PENDING
                "in progress" -> TaskStatus.IN_PROGRESS
                "completed" -> TaskStatus.COMPLETED
                else -> TaskStatus.PENDING
            }
        } catch (e: Exception) {
            TaskStatus.PENDING
        }
    }
}
