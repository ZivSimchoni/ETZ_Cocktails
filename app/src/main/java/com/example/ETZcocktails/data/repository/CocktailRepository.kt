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

    fun addItem(cocktail:Cocktail) {
        cocktailDao?.addItem(cocktail)
    }

    fun deleteItem(cocktail: Cocktail) {
        cocktailDao?.deleteItem(cocktail)
    }

    fun getItem(id:Int)  = cocktailDao?.getItem(id)

    fun deleteAll() {
        cocktailDao?.deleteAll()
    }

    fun getMaxId() = cocktailDao?.getMaxId()
}