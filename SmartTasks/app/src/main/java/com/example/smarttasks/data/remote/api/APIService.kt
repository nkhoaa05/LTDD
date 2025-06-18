package com.example.smarttasks.data.remote.api

import com.example.smarttasks.data.model.Task
import com.example.smarttasks.data.model.TaskApiResponse

import retrofit2.http.*

interface TaskApiService {

    @GET("researchUTH/tasks")
    suspend fun getAllTasks(): TaskApiResponse

    @GET("researchUTH/task/{id}")
    suspend fun getTaskById(@Path("id") id: String): Task

    @DELETE("researchUTH/task/{id}")
    suspend fun delTask(@Path("id") id: String): Task


}
