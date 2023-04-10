package com.musict.shayarihelper

data class CategoryModel(var id:Int ,var CategoryName:String)

data class DisplayCategoryModelData(
    var shyari_id: Int, var shyari_item: String,
    var category_id: Int,var status : Int
)

data class favoratshayri(var  id:Int,var quotes:String , var status:Int)