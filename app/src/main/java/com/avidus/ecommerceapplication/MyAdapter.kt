package com.avidus.ecommerceapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.Product
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Activity, private val list: List<Product?>?) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list?.get(position)
        currentItem?.let {
            holder.title.text = it.title
            Picasso.get().load(it.thumbnail).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ShapeableImageView = itemView.findViewById(R.id.productImage)
        val title: TextView = itemView.findViewById(R.id.productName)
    }
}
