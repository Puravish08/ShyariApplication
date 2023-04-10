package com.musict.shayarihelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MyDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    private val mDataBase: SQLiteDatabase? = null
    private var mNeedUpdate = false
    private val mContext: Context

    init {

        if (Build.VERSION.SDK_INT >= 17) DB_PATH = context.applicationInfo.dataDir + "/databases/" else DB_PATH =
            "/data/data/" + context.packageName + "/databases/"
        mContext = context
        copyDataBase()
        this.readableDatabase

    }

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }

        }

    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(DB_PATH + DB_NAME)
        return dbFile.exists()
    }

    //    TODO copy file
    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = mContext.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(DB_PATH + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) mNeedUpdate = true
    }

    //    TODO update database
    @Throws(IOException::class)
    fun updateDataBase() {
        if (mNeedUpdate) {

            val dbFile = File(DB_PATH + DB_NAME)
            if (dbFile.exists()) dbFile.delete()
            copyDataBase()
            mNeedUpdate = false

        }

    }

    @Synchronized
    override fun close() {
        mDataBase?.close()
        super.close()
    }



    fun categoryData(): ArrayList<CategoryModel> {

        var list=ArrayList<CategoryModel>()

        val db = readableDatabase
        val sql = "select * from categoryTb"
        val c = db.rawQuery(sql, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(0)
                val categoryName = c.getString(1)

                Log.e(TAG, "readData:==> $id   $categoryName  ")

                var model = CategoryModel(id,categoryName)
                list.add(model)

            } while (c.moveToNext())

        }

        return list
    }


 fun shayarisdata(cat_id: Int): ArrayList<DisplayCategoryModelData> {

        var shayarilist=ArrayList<DisplayCategoryModelData>()

        val sdb = readableDatabase

        val ssql = "select * from ShayariTb where category_Id='$cat_id'"
        val p = sdb.rawQuery(ssql,null)
        if (p.count > 0) {
            p.moveToFirst()
            do {
                val shyari_id = p.getInt(0)
                val shyari = p.getString(1)
                val categori_id = p.getInt(2)
                var status = p.getInt(3)

                Log.e(TAG, "readData:==> $shyari_id   $shyari")


                var smodel = DisplayCategoryModelData(shyari_id , shyari , categori_id,status )
                shayarilist.add(smodel)


            } while (p.moveToNext())
        }


     return shayarilist
    }


    fun fv_update_data(id:Int,status:Int){

        val update = writableDatabase
        val updatesql = "update ShayariTb set statusfav='$status' where shayari_id='$id'"
        update.execSQL(updatesql)

    }


    fun status_dis(): ArrayList<favoratshayri> {
        var displayshyari = ArrayList<favoratshayri>()


        val Shayaridb = readableDatabase
        val shyarisql = "select * from ShayariTb where statusfav =1"
        val cursor = Shayaridb.rawQuery(shyarisql, null)
//        Shayaridb.execSQL(shyarisql, null)



        if (cursor.count > 0) {
            cursor.moveToFirst()
                do {
                    var id = cursor.getInt(0)
                    var shyari = cursor.getString(1)
                    var status = cursor.getInt(2)


                    Log.e("TAG", "status_dis: $id $shyari")
                    var favoratshayri = favoratshayri(id, shyari, status)

                    displayshyari.add(favoratshayri)
                } while (cursor.moveToNext())
            }
            return displayshyari
    }


    companion object {

        private const val TAG = "MyDatabase"
        private const val DB_NAME = "Shayari database.db"
        private var DB_PATH = ""
        private const val DB_VERSION = 1

    }
}