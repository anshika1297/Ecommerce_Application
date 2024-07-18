package com.avidus.ecommerceapplication

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.avidus.ecommerceapplication.data.Product
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (val context : Activity, val list: ArrayList<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//Layout Manage fails to create view for some data then this method is used

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

val itemView = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false)
        return MyViewHolder(itemView)
    }

    //populate data in the View
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
 val currentItem = list[position]
        holder.title.text=currentItem.title

    }

    override fun getItemCount(): Int {
return list.size
    }

    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

var image:ShapeableImageView
var title: TextView

init {
    image=itemView.findViewById(R.id.productImage)
    title=itemView.findViewById(R.id.productName)
}
    }
}