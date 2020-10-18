package com.example.recepiappkotlin.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.models.DatabaseModel
import com.example.recepiappkotlin.repositories.DatabaseRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recipe.view.*

class DatabaseAdapter(private val context: Context, private val list: MutableList<DatabaseModel>): RecyclerView.Adapter<DatabaseAdapter.MyViewHolder>() {

    var repository = DatabaseRepository()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_recipe, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_name.text = list[position].title
        holder.desc.text = list[position].desc
        Picasso.get()
            .load(list[position].image)
            .placeholder(com.example.recepiappkotlin.R.drawable.nophoto)
            .into(holder.image)

        holder.layout.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {

                val alterBox =
                    AlertDialog.Builder(v?.getRootView()?.getContext()).setTitle("Delete")
                        .setMessage("Are you sure you want to delete recipe?")
                        .setPositiveButton(
                            android.R.string.yes
                        ) { dialogInterface, i -> list[position].key?.let{repository.deleteRecipe(it)}
                        }
                        .setNegativeButton(android.R.string.no, null)

                alterBox.show()
                return true
            }
        })
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_name : TextView
        var desc : TextView
        var image : ImageView
        var layout : LinearLayout

        init {
            txt_name = itemView.txt_name
            desc = itemView.txt_desc
            image = itemView.image_movie
            layout = itemView.layout
        }
    }
}