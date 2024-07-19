package com.avidus.ecommerceapplication.data


import com.google.gson.annotations.SerializedName

class Category : ArrayList<Category.CategoryItem>(){
    data class CategoryItem(
        @SerializedName("name")
        val name: String?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("url")
        val url: String?
    )
}