package com.avidus.ecommerceapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.Category

class CategoryAdapter(
    private val context: Activity,
    private var categories: List<Category.CategoryItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: Category.CategoryItem)
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryNameTextView: TextView = view.findViewById(R.id.categoryNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryNameTextView.text = category.name

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(category)
        }
    }

    override fun getItemCount(): Int = categories.size

    fun updateList(newCategories: List<Category.CategoryItem>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}
