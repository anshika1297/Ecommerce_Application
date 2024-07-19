package com.avidus.ecommerceapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.MyDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchProductActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchProductAdapter: SearchProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        recyclerView = findViewById(R.id.searchProductList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val searchQuery = intent.getStringExtra("searchQuery") ?: ""

        if (searchQuery.isNotEmpty()) {
            fetchSearchResults(searchQuery)
        }
    }

    private fun fetchSearchResults(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        val searchCall = apiInterface.searchProducts(query)

        searchCall.enqueue(object : Callback<MyDataClass> {
            override fun onResponse(call: Call<MyDataClass>, response: Response<MyDataClass>) {
                val productList = response.body()?.products ?: emptyList()
                searchProductAdapter = SearchProductAdapter(productList)
                recyclerView.adapter = searchProductAdapter
            }

            override fun onFailure(call: Call<MyDataClass>, t: Throwable) {
                // Handle failure
            }
        })
    }
}