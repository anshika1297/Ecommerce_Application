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

        val url = intent.getStringExtra("url") ?: "https://dummyjson.com/products"
        fetchData(url)
    }

    private fun fetchData(url: String) {
        println("the url is $url")
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("$url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductsByUrl(url)
        retrofitData.enqueue(object : Callback<MyDataClass?> {
            override fun onResponse(
                call: Call<MyDataClass?>,
                response: Response<MyDataClass?>
            ) {
                val responseBody = response.body()
                val productList = responseBody?.products
                 println("${productList} here the Data is")
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
