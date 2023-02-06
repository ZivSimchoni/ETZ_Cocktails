package com.example.ETZcocktails.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ETZcocktails.Cocktail

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(cocktail:Cocktail)

    @Delete
    fun deleteItem(vararg  cocktails:Cocktail)

    @Update
    fun updateItem(cocktail: Cocktail)

    @Query("SELECT * FROM cocktails ORDER BY strDrink ASC")
    fun getItems() : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails ORDER BY strDrink ASC")
    fun getListCocktails() : List<Cocktail>

    @Query("SELECT * FROM cocktails WHERE id =:id")
    fun getItem(id:Int) : Cocktail

    @Query("DELETE FROM cocktails")
    fun deleteAll()

    @Query("SELECT MAX(id) FROM cocktails")
    fun getMaxId(): Int

}