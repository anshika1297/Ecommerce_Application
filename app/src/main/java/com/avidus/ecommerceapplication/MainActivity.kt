package com.avidus.ecommerceapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.MyDataClass
import com.avidus.ecommerceapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.ProductList

        val retrofitBuilder = Retrofit.Builder()
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
                val responseBody = response.body()
                val productList = responseBody?.products

                if (productList != null) {
                    myAdapter = MyAdapter(this@MainActivity, productList)
                    recyclerView.adapter = myAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                } else {
                    // Handle the case where productList is null
                }
            }

            override fun onFailure(call: Call<MyDataClass?>, t: Throwable) {
                // Handle the failure case
            }
        })
    }
}
