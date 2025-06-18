package com.example.th_5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val api = Retrofit.Builder()
        .baseUrl("https://mock.apidog.com/m1/890655-872447-default/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductAPI::class.java)

    init { 
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            try {
                val result = api.getProduct()
                _product.value = result
            } catch (e: Exception) {
                // Xử lý lỗi ở đây
                e.printStackTrace()
            }
        }
    }
}
