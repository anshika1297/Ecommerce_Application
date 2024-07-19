package com.avidus.ecommerceapplication

import com.avidus.ecommerceapplication.data.Category
import com.avidus.ecommerceapplication.data.MyDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<MyDataClass>
@GET("categories")
fun getCategories(): Call<Category>
    @GET
    fun getProductsByUrl(@Url url: String): Call<MyDataClass>
    @GET("search")
    fun searchProducts(@Query("q") query: String): Call<MyDataClass>
}