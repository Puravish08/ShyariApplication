package com.musict.shayarihelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.CategoryAdapter
import com.musict.shayarihelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     lateinit var  mainBinding : ActivityMainBinding

     lateinit var adapter: CategoryAdapter

     var categorylist= ArrayList<CategoryModel>()
    lateinit var db:MyDatabase


 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        db= MyDatabase(this)
        initview()

    }

    private fun initview() {



        categorylist = db.categoryData()



        adapter = CategoryAdapter(categorylist) {
            var i = Intent(this, DisplayCategory::class.java)
            i.putExtra("Title", it.CategoryName)
            i.putExtra("id", it.id)
            startActivity(i)

            Log.e("TAG", "initview: " +it.id )



        }
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.rcv.layoutManager = manager
        mainBinding.rcv.adapter = adapter

    }
}