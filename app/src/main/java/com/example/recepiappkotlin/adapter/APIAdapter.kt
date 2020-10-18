package com.example.recepiappkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.models.APIModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recipe.view.*

class APIAdapter(private val context: Context, private val list: MutableList<APIModel>): RecyclerView.Adapter<APIAdapter.MyViewHolder>() {
    lateinit var listener: onClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_recipe, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(list[position].image_url).into(holder.image)
        holder.txt_name.text = list[position].title

        holder.layout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                listener.onItemClick(list[position])
            }



        })
    }

    fun setOnItemClickListener(listener1: onClickListener): Unit{
        this.listener = listener1
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_name : TextView
        var image : ImageView
        var layout : LinearLayout

        init {
            txt_name = itemView.txt_name
            image = itemView.image_movie
            layout = itemView.layout
        }
    }
}