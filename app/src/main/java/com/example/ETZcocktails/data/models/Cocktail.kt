package com.example.ETZcocktails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
class Cocktail( _id:Int ?=0,_idDrink: Long ?= null,_strDrink: String? = null,_strAlcoholic:String? = null,_strInstructions: String? = null,_strDrinkThumb: String? = null,_strIngredient1: String? = null,_strIngredient2: String? = null,_strIngredient3: String? = null,_strIngredient4: String? = null,_strIngredient5: String? = null,_strMeasure1: String? = null,_strMeasure2: String? = null,_strMeasure3: String? = null,_strMeasure4: String? = null,_strMeasure5: String? = null) {

    @PrimaryKey()
    var id:Int ?= _id

    var idDrink: Long? = _idDrink
    var strDrink: String? = _strDrink
    var strAlcoholic: String? =_strAlcoholic
    var strInstructions: String? = _strInstructions
    var strDrinkThumb: String? = _strDrinkThumb

    var strIngredient1: String? = _strIngredient1
    var strIngredient2: String? = _strIngredient2
    var strIngredient3: String? = _strIngredient3
    var strIngredient4: String? = _strIngredient4
    var strIngredient5: String? = _strIngredient5

    var strMeasure1: String? = _strMeasure1
    var strMeasure2: String? = _strMeasure2
    var strMeasure3: String? = _strMeasure3
    var strMeasure4: String? = _strMeasure4
    var strMeasure5: String? = _strMeasure5

    fun setByColumn(column: String, value: String) {
        when (column) {
            "id" -> id = value.toInt()
            "idDrink" -> idDrink = value.toLong()
            "strDrink" -> strDrink = value
            "strAlcoholic" -> strAlcoholic = value
            "strInstructions" -> strInstructions = value
            "strDrinkThumb" -> strDrinkThumb = value
            "strIngredient1" -> strIngredient1 = value
            "strIngredient2" -> strIngredient2 = value
            "strIngredient3" -> strIngredient3 = value
            "strIngredient4" -> strIngredient4 = value
            "strIngredient5" -> strIngredient5 = value
            "strMeasure1" -> strMeasure1 = value
            "strMeasure2" -> strMeasure2 = value
            "strMeasure3" -> strMeasure3 = value
            "strMeasure4" -> strMeasure4 = value
            "strMeasure5" -> strMeasure5 = value
        }
    }


    override fun toString(): String {
        return "id: " + idDrink + " " + strDrink +" " + strAlcoholic +" " + strInstructions +" " + strDrinkThumb +" " +
                strIngredient1 +" " + strIngredient2 +" " + strIngredient3 +" " + strIngredient4 +" " + strIngredient5 +" "+
                strMeasure1 +" " + strMeasure2 +" " + strMeasure3 +" " + strMeasure4 +" " + strMeasure5
    }

    fun copy(): Cocktail {
        return Cocktail(id,idDrink,strDrink,strAlcoholic,strInstructions,strDrinkThumb,strIngredient1,strIngredient2,strIngredient3,strIngredient4,strIngredient5,strMeasure1,strMeasure2,strMeasure3,strMeasure4,strMeasure5)

    }
}

data class CocktailList(
    val drinks: List<Cocktail>? = null
)