package com.musict.shayarihelper

import android.content.Intent
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.FavouritShayariAdapter
import com.musict.shayarihelper.databinding.ActivityFavouritShayariCollacterBinding
import com.musict.shayarihelper.databinding.ShayariCollacterDesignItemBinding

class favourit_shayari_collacter : AppCompatActivity() {

    lateinit var binding: ActivityFavouritShayariCollacterBinding
    lateinit var shayaridb: MyDatabase
    var shayarilist = ArrayList<favoratshayri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouritShayariCollacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        shayaridb = MyDatabase(this)
        initview()
    }

    private fun initview() {


        shayarilist = shayaridb.status_dis()

        var adapter = FavouritShayariAdapter ({ id, status ->

            shayaridb.fv_update_data(id, status)

        })


        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rcvfav.layoutManager = manager
        binding.rcvfav.adapter = adapter

        adapter.updatelist(shayarilist)


    }
}