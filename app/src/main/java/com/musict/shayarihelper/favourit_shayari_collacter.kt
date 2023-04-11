package com.musict.shayarihelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.FavouritShayariAdapter
import com.musict.shayarihelper.databinding.ActivityFavouritShayariCollacterBinding

class favourit_shayari_collacter : AppCompatActivity() {

    lateinit var binding: ActivityFavouritShayariCollacterBinding
    lateinit var shayaridb: MyDatabase
    var shayarilist = ArrayList<favoratshayri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritShayariCollacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = ContextCompat.getColor(this, R.color.onebigshyari)
//        }

        shayaridb = MyDatabase(this)
        initview()
    }

    private fun initview() {


        binding.backimg.setOnClickListener {

            onBackPressed()
        }


        shayarilist = shayaridb.status_dis()

        var adapter = FavouritShayariAdapter { id, status ->

            shayaridb.fv_update_data(id, status)

        }
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rcvfav.layoutManager = manager
        binding.rcvfav.adapter = adapter

        adapter.updatelist(shayarilist)


    }
}