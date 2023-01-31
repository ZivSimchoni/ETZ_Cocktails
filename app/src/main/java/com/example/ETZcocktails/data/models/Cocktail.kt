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


class CocktailTEST {
    var idDrink: Long? = null
    var strDrink: String? = null
    var strInstructions: String? = null
    var strDrinkThumb: String? = null

    var strIngredient1: String? = null
    var strIngredient2: String? = null
    var strIngredient3: String? = null
    var strIngredient4: String? = null
    var strIngredient5: String? = null

    var strMeasure1: String? = null
    var strMeasure2: String? = null
    var strMeasure3: String? = null
    var strMeasure4: String? = null
    var strMeasure5: String? = null

    override fun toString(): String {
        return "id:" + idDrink.toString()+"   " + strDrink+strInstructions+strDrinkThumb+strIngredient1+strIngredient2+strIngredient3+strIngredient4+strIngredient5+strMeasure1+strMeasure2+strMeasure3+strMeasure4+strMeasure5
    }
}

data class CocktailList(
    val drinks: List<CocktailTEST>? = null
)