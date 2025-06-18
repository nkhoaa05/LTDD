package com.example.smarttasks.repository

import android.util.Log
import com.example.smarttasks.data.model.Task
import com.example.smarttasks.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: String): Task?
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: String)
}

class TaskRepositoryImpl : TaskRepository {

    private val apiService = RetrofitClient.apiService

//    override fun getAllTasks(): Flow<List<Task>> = flow {
//        try {
//            val response = apiService.getAllTasks()
//            if (response.isSuccess) {
//                emit(response.data)
//            } else {
//                emit(emptyList()) // hoặc throw Exception(response.message)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(emptyList())
//        }
//    }

    override fun getAllTasks(): Flow<List<Task>> = flow {
        try {
            val response = apiService.getAllTasks()
            Log.d("API_SUCCESS", "data: ${response.data}")
            emit(response.data)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Không gọi được API: ${e.message}", e)
            emit(emptyList())
        }
    }


    override suspend fun getTaskById(id: String): Task? {
        return try {
            apiService.getTaskById(id)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertTask(task: Task) = Unit
    override suspend fun updateTask(task: Task) = Unit
    override suspend fun deleteTask(id: String) = Unit
}