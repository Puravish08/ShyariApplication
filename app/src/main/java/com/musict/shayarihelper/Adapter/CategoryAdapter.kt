package com.musict.shayarihelper.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.musict.shayarihelper.CategoryModel
import com.musict.shayarihelper.R

class CategoryAdapter(var  categorylist: ArrayList<CategoryModel>, var click:(CategoryModel)->Unit) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryName:TextView=itemView.findViewById(R.id.txtcategory)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var v=LayoutInflater.from(parent.context).inflate(R.layout.category_list,parent,false)
        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.categoryName.text=categorylist[position].CategoryName

        holder.categoryName.setOnClickListener {
            click.invoke(categorylist[position])
        }
    }

    override fun getItemCount(): Int {
        return categorylist.size

    }



}