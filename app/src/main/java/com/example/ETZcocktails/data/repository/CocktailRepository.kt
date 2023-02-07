package com.example.ETZcocktails.data.repository

import android.app.Application
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.data.local_db.CocktailDao
import com.example.ETZcocktails.data.local_db.CocktailDataBase

class CocktailRepository(application: Application) {

    private var cocktailDao:CocktailDao?

    init {
        val db  = CocktailDataBase.getDatabase(application.applicationContext)
        cocktailDao = db .cocktailsDao()
    }

    fun getItems() = cocktailDao?.getItems()

    fun getListCocktails() =cocktailDao?.getListCocktails()

    fun getListCocktailsByMe() = cocktailDao?.getListCocktailsByMe()

    fun getListFavoriteCocktails() = cocktailDao?.getListFavoriteCocktails()

    fun addItem(cocktail:Cocktail) {
        cocktailDao?.addItem(cocktail)
    }
    fun addFavouriteItem(cocktail:Cocktail) {
        cocktail.id= cocktailDao?.getMaxId()?.plus(1) ?: 0
        cocktailDao?.addItem(cocktail)
    }
    fun deleteItemIdDrink(id:Long) {
        cocktailDao?.deleteItemIdDrink(id)
    }
    fun deleteItem(cocktail: Cocktail) {
        cocktailDao?.deleteItem(cocktail)
    }

    fun deleteMyCocktails() {
        cocktailDao?.deleteMyCocktails()
    }


    fun getItemIdDrink(id:Long)  = cocktailDao?.getItemIdDrink(id)

    fun deleteAll() {
        cocktailDao?.deleteAll()
    }

    fun getMaxId() = cocktailDao?.getMaxId()

    fun getMinIdDrink()= cocktailDao?.getMinIdDrink()
}