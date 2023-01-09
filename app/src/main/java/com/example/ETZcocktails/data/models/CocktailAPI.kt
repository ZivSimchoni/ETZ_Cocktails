package com.example.ETZcocktails.data.models

import com.example.ETZcocktails.CocktailList
import retrofit2.Call
import retrofit2.http.GET


interface CocktailAPI {
    @GET("search.php?s=margarita")
    fun fetchACock() : Call<CocktailList?>?

}
