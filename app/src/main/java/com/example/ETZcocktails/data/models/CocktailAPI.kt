package com.example.ETZcocktails.data.models

import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailAPI {
    @GET("search.php?s=")
    fun FetchCocktailByName(@Query("s") CockToSearch:String) : Call<CocktailList?>? // what 's' stand for?

    // Get Ingredient
    @GET("filter.php?i=")
    fun FetchCocktailByIng(@Query("i") IngToSearch:String) : Call<CocktailList?>?

    @GET("random.php")
    fun FetchRandomCocktail() : Call<CocktailList?>?
}
