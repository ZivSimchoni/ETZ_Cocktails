package com.example.ETZcocktails.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ETZcocktails.Cocktail

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(cocktail:Cocktail)

    @Delete
    fun deleteItem(cocktail:Cocktail)

    @Query("DELETE FROM cocktails WHERE IdDrink = :id")
    fun deleteItemIdDrink(id:Long)

    @Update
    fun updateItem(cocktail: Cocktail)

    @Query("SELECT * FROM cocktails ORDER BY strDrink ASC")
    fun getItems() : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails ORDER BY strDrink ASC")
    fun getListCocktails() : List<Cocktail>

    @Query("SELECT * FROM cocktails WHERE idDrink=-1 ORDER BY strDrink ASC")
    fun getListCocktailsByMe(): List<Cocktail>

    @Query("SELECT * FROM cocktails WHERE idDrink!=-1 ORDER BY strDrink ASC")
    fun getListFavoriteCocktails(): List<Cocktail>

    @Query("SELECT * FROM cocktails WHERE idDrink =:id")
    fun getItemIdDrink(id:Long) : Cocktail

    @Query("DELETE FROM cocktails")
    fun deleteAll()

    @Query("SELECT MAX(id) FROM cocktails")
    fun getMaxId(): Int

}