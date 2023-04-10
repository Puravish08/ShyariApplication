package com.musict.shayarihelper

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.DisplayDataAdapter
import com.musict.shayarihelper.Adapter.FavouritShayariAdapter
import com.musict.shayarihelper.databinding.ActivityDisplayCategoryBinding

class DisplayCategory : AppCompatActivity() {

    lateinit var disbinding : ActivityDisplayCategoryBinding
    lateinit var dbD : MyDatabase
    var sharlist= ArrayList<DisplayCategoryModelData>()
    lateinit var adapter: DisplayDataAdapter

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


        var categoryName:String?=intent.getStringExtra("Title")

        disbinding.txtdisplaytitle.text=categoryName





        var  cat_id = intent.getIntExtra("id",0)
        sharlist = dbD.shayarisdata(cat_id)


            adapter = DisplayDataAdapter(sharlist, {


                var i = Intent(this, OneBigShyariActivity::class.java)
                i.putExtra("shayari", it.shyari_item)
                startActivity(i)
            }, { id, fav ->

                dbD.fv_update_data(id, fav)


            })
            var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            disbinding.rcvdisplaycat.layoutManager = manager
            disbinding.rcvdisplaycat.adapter = adapter





        disbinding.backimg.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }



            disbinding.imglikefv.setOnClickListener {
                var pass = Intent(this, favourit_shayari_collacter::class.java)
                startActivity(pass)
            }






    }


}