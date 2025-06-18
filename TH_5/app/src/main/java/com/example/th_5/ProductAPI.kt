package com.example.th_5

import retrofit2.http.GET
interface ProductAPI {
    @GET("product")
    suspend fun getProduct(): Product
}