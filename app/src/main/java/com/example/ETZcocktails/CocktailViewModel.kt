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

    fun getListCocktails() = repository.getListCocktails()

    fun deleteItem(cocktail: Cocktail) {
        repository.deleteItem(cocktail)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun getMaxId() = repository.getMaxId()
}
