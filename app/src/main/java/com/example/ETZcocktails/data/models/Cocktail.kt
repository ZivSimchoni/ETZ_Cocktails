package com.example.ETZcocktails

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "cocktails")
data class Cocktail(

    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id:Int,
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id : Int = 0,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "instructions")
    val instructions:String,

    @ColumnInfo(name = "tags")
    val tags:String,

    @ColumnInfo(name = "iba")
    val iba:String,

    @ColumnInfo(name = "category")
    val category:String,

    @ColumnInfo(name = "alcoholic")
    val alcoholic:String,

    @ColumnInfo(name = "glass")
    val glass:String,

    @ColumnInfo(name = "ingredient1")
    val ingredient1:String,
    @ColumnInfo(name = "ingredient2")
    val ingredient2:String,
    @ColumnInfo(name = "ingredient3")
    val ingredient3:String,
    @ColumnInfo(name = "ingredient4")
    val ingredient4:String,
    @ColumnInfo(name = "ingredient5")
    val ingredient5:String,
    @ColumnInfo(name = "ingredient6")
    val ingredient6:String,
    @ColumnInfo(name = "ingredient7")
    val ingredient7:String,
    @ColumnInfo(name = "ingredient8")
    val ingredient8:String,
    @ColumnInfo(name = "ingredient9")
    val ingredient9:String,
    @ColumnInfo(name = "ingredient10")
    val ingredient10:String,
    // Haven't seen any entry with more than 5, but just in-case
//    @ColumnInfo(name = "ingredient11")
//    val ingredient11:String,
//    @ColumnInfo(name = "ingredient12")
//    val ingredient12:String,
//    @ColumnInfo(name = "ingredient13")
//    val ingredient13:String,
//    @ColumnInfo(name = "ingredient14")
//    val ingredient14:String,
//    @ColumnInfo(name = "ingredient15")
//    val ingredient15:String,

    @ColumnInfo(name = "measure1")
    val measure1:String,
    @ColumnInfo(name = "measure2")
    val measure2:String,
    @ColumnInfo(name = "measure3")
    val measure3:String,
    @ColumnInfo(name = "measure4")
    val measure4:String,
    @ColumnInfo(name = "measure5")
    val measure5:String,
    @ColumnInfo(name = "measure6")
    val measure6:String,
    @ColumnInfo(name = "measure7")
    val measure7:String,
    @ColumnInfo(name = "measure8")
    val measure8:String,
    @ColumnInfo(name = "measure9")
    val measure9:String,
    @ColumnInfo(name = "measure10")
    val measure10:String,
    // Haven't seen any entry with more than 5, but just in-case
//    @ColumnInfo(name = "measure11")
//    val measure11:String,
//    @ColumnInfo(name = "measure12")
//    val measure12:String,
//    @ColumnInfo(name = "measure13")
//    val measure13:String,
//    @ColumnInfo(name = "measure14")
//    val measure14:String,
//    @ColumnInfo(name = "measure15")
//    val measure15:String,

    @ColumnInfo(name = "cocktail_thumb")
    val photo: String?)