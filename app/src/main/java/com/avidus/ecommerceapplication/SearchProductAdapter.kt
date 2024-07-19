package com.avidus.ecommerceapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.Product
import com.squareup.picasso.Picasso

class SearchProductAdapter(private val products: List<Product?>) : RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.productName)
        private val imageProduct= itemView.findViewById<ImageView>(R.id.productImage)

        // Add other views as needed

        fun bind(product: Product) {
            nameTextView.text = product.title ?: "No Title"
            Picasso.get().load(product.thumbnail).into(imageProduct)

            // Bind other fields as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        products[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = products.size
}