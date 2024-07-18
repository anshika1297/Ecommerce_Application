package com.avidus.ecommerceapplication

import com.avidus.ecommerceapplication.data.MyDataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getProducts(): Call<MyDataClass>

}