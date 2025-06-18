package com.example.smarttasks.data.remote


import com.example.smarttasks.data.model.TaskCategory
import com.example.smarttasks.data.model.TaskCategoryAdapter
import com.example.smarttasks.data.model.TaskPriority
import com.example.smarttasks.data.model.TaskPriorityAdapter
import com.example.smarttasks.data.model.TaskStatus
import com.example.smarttasks.data.model.TaskStatusAdapter
import com.example.smarttasks.data.remote.api.TaskApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://amock.io/api/"

    //    researchUTH/
    private val gson = GsonBuilder()
        .registerTypeAdapter(TaskCategory::class.java, TaskCategoryAdapter())
        .registerTypeAdapter(TaskPriority::class.java, TaskPriorityAdapter())
        .registerTypeAdapter(TaskStatus::class.java, TaskStatusAdapter())
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        .create()

    val apiService: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TaskApiService::class.java)
    }
}
