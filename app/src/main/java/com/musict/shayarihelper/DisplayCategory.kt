package com.musict.shayarihelper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.DisplayDataAdapter
import com.musict.shayarihelper.databinding.ActivityDisplayCategoryBinding


class DisplayCategory : AppCompatActivity() {

    lateinit var disbinding : ActivityDisplayCategoryBinding
    lateinit var dbD : MyDatabase
    var sharlist= ArrayList<DisplayCategoryModelData>()
    lateinit var adapter: DisplayDataAdapter
    var cat_id: Int = 0

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disbinding = ActivityDisplayCategoryBinding.inflate(layoutInflater)
        setContentView(disbinding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.categorinext)
        }


        dbD = MyDatabase(this)
        initview()
    }

    private fun initview() {


        val categoryName:String?=intent.getStringExtra("Title")

        disbinding.txtdisplaytitle.text=categoryName



          cat_id = intent.getIntExtra("id",0)
//        sharlist = dbD.shayarisdata(cat_id)


            adapter = DisplayDataAdapter(this, {


                var i = Intent(this, OneBigShyariActivity::class.java)
                i.putExtra("shayari", it.shyari_item)
                startActivity(i)
                finish()
            }, { id, fav ->

                dbD.fv_update_data(id, fav)




            })
            val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            disbinding.rcvdisplaycat.layoutManager = manager
            disbinding.rcvdisplaycat.adapter = adapter


        disbinding.backimg.setOnClickListener {

            onBackPressed()
        }

            disbinding.imglikefv.setOnClickListener {
                var pass = Intent(this, favourit_shayari_collacter::class.java)
                startActivity(pass)
            }
    }
    override fun onResume() {

        super.onResume()
        sharlist = dbD.shayarisdata(cat_id)
        adapter.updatelist(sharlist)
        Log.e("TAG", "onResume: "+cat_id)
    }


}