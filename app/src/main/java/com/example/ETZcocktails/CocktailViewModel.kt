package com.example.ETZcocktails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ETZcocktails.data.repository.CocktailRepository

class CocktailViewModel (application: Application)  : AndroidViewModel(application){

    private val repository = CocktailRepository(application)

    val items : LiveData<List<Cocktail>>? = repository.getItems()

    private val _chosenItem = MutableLiveData<Cocktail>()

    val chosenItem : LiveData<Cocktail> get() = _chosenItem

    fun setItem(cocktail: Cocktail) {
        _chosenItem.value = cocktail
    }

    fun addItem(cocktail: Cocktail) {
        repository.addItem(cocktail)
    }

    fun addFavItem(cocktail: Cocktail) {
        repository.addFavouriteItem(cocktail)
    }

    fun getListCocktails() = repository.getListCocktails()

    fun getListCocktailsByMe() = repository.getListCocktailsByMe()

    fun getListFavoriteCocktails() = repository.getListFavoriteCocktails()

    fun deleteItem(cocktail: Cocktail) {
        repository.deleteItem(cocktail)
    }

    fun deleteMyCocktails(){
        repository.deleteMyCocktails()
    }

    fun getItemIdDrink(id:Long) = repository.getItemIdDrink(id)

    fun deleteItemIdDrink(id:Long) {
        repository.deleteItemIdDrink(id)
    }
    fun deleteAll() {
        repository.deleteAll()
    }

    fun getMaxId() = repository.getMaxId()

    fun getMinIdDrink()= repository.getMinIdDrink()
}
