package com.musict.shayarihelper.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.musict.shayarihelper.DisplayCategoryModelData
import com.musict.shayarihelper.R

class DisplayDataAdapter(var sharList : ArrayList<DisplayCategoryModelData>,var dataclick:(DisplayCategoryModelData)-> Unit,
var like:(Int,Int)-> Unit)  :
    RecyclerView.Adapter<DisplayDataAdapter.MyViewHolder>() {




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ShayariItem:TextView=itemView.findViewById(R.id.txtshayari)
        var imglike:ImageView=itemView.findViewById(R.id.heartlike)
        var share:ImageView=itemView.findViewById(R.id.share)

//        var linear : LinearLayout=itemView.findViewById(R.id.lineardis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        var v = LayoutInflater.from(parent.context).inflate(R.layout.display_category_data,parent,false)
        return MyViewHolder(v)

    }

    override fun getItemCount(): Int {
        return sharList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.ShayariItem.text=sharList[position].shyari_item

        holder.ShayariItem.setOnClickListener{

            dataclick.invoke(sharList[position])
        }

//        holder.share.setOnClickListener {
//
//
//            val p = Intent(Intent.ACTION_SEND)
//            p.type = "text/plan"
//
//            p.putExtra(Intent.EXTRA_SUBJECT, "Share")
////            p.putExtra(Intent.EXTRA_TEXT, " " + shayari)
////            startActivity(Intent.createChooser(p, "Share"))
//
//
//
//        }


            if (sharList[position].status==1)
            {

                holder.imglike.setImageResource(R.drawable.redlike)

            }

            else
            {
                holder.imglike.setImageResource(R.drawable.whiteborderlike)

            }



        holder.imglike.setOnClickListener {

            if (sharList[position].status==1)
            {

                like.invoke(sharList[position].shyari_id,0)
                holder.imglike.setImageResource(R.drawable.whiteborderlike)
                sharList[position].status=0
            }
            else
            {
                like.invoke(sharList[position].shyari_id,1)
                holder.imglike.setImageResource(R.drawable.redlike)
                sharList[position].status=1
            }



        }



//        disbinding.share.setOnClickListener {
//
//
//            val p = Intent(Intent.ACTION_SEND)
//            p.type = "text/plan"
//
//            p.putExtra(Intent.EXTRA_SUBJECT, "Share")
//            p.putExtra(Intent.EXTRA_TEXT, " " + shayari)
//            startActivity(Intent.createChooser(p, "Share"))
//
//
//        }
















    }


}