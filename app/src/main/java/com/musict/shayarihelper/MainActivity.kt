package com.musict.shayarihelper

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.musict.shayarihelper.Adapter.CategoryAdapter
import com.musict.shayarihelper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

     lateinit var  mainBinding : ActivityMainBinding

     lateinit var adapter: CategoryAdapter

     var categorylist= ArrayList<CategoryModel>()

    lateinit var textView: TextView

    lateinit var db:MyDatabase

    lateinit var toggle:ActionBarDrawerToggle

//    var slidrInterface: SlidrInterface? = null



 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        db= MyDatabase(this)
        initview()

//     slidrInterface = Slidr.attach(this);


     textView = findViewById(R.id.version)


     val versionRelease = Build.VERSION.RELEASE

     val version = Build.VERSION.SDK_INT



     textView.setText("Version Release "+versionRelease+"\nVersion "+version)

 }

    private fun initview() {




//        fun Any.unlockSlide(v: Any) {
//            slidrInterface?.unlock();
//        }




        categorylist = db.categoryData()



        adapter = CategoryAdapter(categorylist) {
            var i = Intent(this, DisplayCategory::class.java)
            i.putExtra("Title", it.CategoryName)
            i.putExtra("id", it.id)
            startActivity(i)

            Log.e("TAG", "initview: " + it.id)


        }
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.rcv.layoutManager = manager
        mainBinding.rcv.adapter = adapter








        mainBinding.menu.setOnClickListener {


            mainBinding.drawblelayout.openDrawer(GravityCompat.START)
        }



        mainBinding.fav.setOnClickListener{
            mainBinding.drawblelayout.closeDrawer(GravityCompat.START)
            var c = Intent(this,favourit_shayari_collacter::class.java)
            startActivity(c)

        }

        mainBinding.exit.setOnClickListener{
            onBackPressed()

        }


        mainBinding.privacy.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://secureprivacy2.blogspot.com/2023/04/shayari-privacy.html")
            )
            startActivity(intent)



        }







        mainBinding.apply {

            toggle = ActionBarDrawerToggle(this@MainActivity,drawblelayout,R.string.open,R.string.close)
            drawblelayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }




        mainBinding.share.setOnClickListener{

            mainBinding.drawblelayout.closeDrawer(GravityCompat.START)
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(

                Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=net.sumitk.quotesmeditation"
            )
            startActivity(share)

        }





        mainBinding.home.setOnClickListener{

            mainBinding.drawblelayout.closeDrawer(GravityCompat.START)
            Toast.makeText(this, "Already In Home", Toast.LENGTH_SHORT).show()


        }



        mainBinding.rateUs.setOnClickListener {

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            } catch (ex: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }




        mainBinding.otherApp.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/dev?id=8865586574070611450")
            )
            startActivity(intent)

        }




    }



    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item))
        {
            true
        }
        return super.onOptionsItemSelected(item)
    }



}