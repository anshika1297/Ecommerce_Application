package com.avidus.ecommerceapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.avidus.ecommerceapplication.data.MyDataClass
import com.avidus.ecommerceapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getProducts()
        retrofitData.enqueue(object : Callback<MyDataClass?> {
            override fun onResponse(
                call: Call<MyDataClass?>,
                response: Response<MyDataClass?>
            ) {

                val responseBody = response.body()!!
                val productList = responseBody.products!!
                binding.textView2.text=productList.toString();

    }
            override fun onFailure(call: Call<MyDataClass?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}