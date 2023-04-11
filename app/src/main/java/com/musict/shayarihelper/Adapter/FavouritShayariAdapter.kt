package com.musict.shayarihelper.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.musict.shayarihelper.R
import com.musict.shayarihelper.favoratshayri

class FavouritShayariAdapter(var likefv:(Int,Int) -> Unit) : RecyclerView.Adapter<FavouritShayariAdapter.myViewHolder>() {
    var fvlist = ArrayList<favoratshayri>()


    class myViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

        var txtfv:TextView = itemview.findViewById(R.id.txtfvshayari)
        var delet:ImageView = itemview.findViewById(R.id.imgdelet)
//        var imgfv:ImageView =itemview.findViewById(R.id.imglikefv)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):myViewHolder {

        var v =LayoutInflater.from(parent.context).inflate(R.layout.shayari_collacter_design_item,parent,false)
        return myViewHolder(v)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder:myViewHolder, position: Int) {

        holder.txtfv.text=fvlist[position].quotes


            holder.delet.setImageResource(R.drawable.redlike)

        holder.delet.setOnClickListener {

            likefv.invoke(fvlist[position].id,0)
            holder.delet.setImageResource(R.drawable.whiteborderlike)
            fvlist[position].status = 0

            deleteitem(position)
        }




    }



    override fun getItemCount(): Int {

        return fvlist.size

    }


    fun updatelist (list: ArrayList<favoratshayri>){

        this.fvlist=list
//        if (list == null && fvlist.size > 0)
//        {

//            fvlist.addAll(list)
            notifyDataSetChanged()
//        }
    }


    private fun deleteitem(position: Int) {
        fvlist.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,fvlist.size)
    }




}