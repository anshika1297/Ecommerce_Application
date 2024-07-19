package com.avidus.ecommerceapplication

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)
        recyclerView = findViewById(R.id.categoryList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this, emptyList(), this)
        recyclerView.adapter = categoryAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        val retrofitData = apiInterface.getCategories()

        retrofitData.enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                val categoryList = response.body()
                if (categoryList != null) {
                    categoryAdapter.updateList(categoryList)
                } else {
                    // Handle the case where categoryList is null
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                // Handle failure
            }
        })
    }

    override fun onItemClick(category: Category.CategoryItem) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("url", category.url)
        startActivity(intent)
    }
}
