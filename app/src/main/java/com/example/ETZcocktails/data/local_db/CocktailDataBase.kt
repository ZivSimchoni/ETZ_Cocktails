package com.example.ETZcocktails.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.data.local_db.CocktailDao

@Database(entities = arrayOf(Cocktail::class), version = 1, exportSchema = false)
abstract class CocktailDataBase : RoomDatabase() {

    abstract fun cocktailsDao() : CocktailDao

    companion object {

        @Volatile
        private var instance:CocktailDataBase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, CocktailDataBase::class.java,"cocktails")
                .allowMainThreadQueries().build()
        }
    }
}