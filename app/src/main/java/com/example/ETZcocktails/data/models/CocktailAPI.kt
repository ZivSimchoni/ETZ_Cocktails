package com.example.ETZcocktails.data.models

import com.example.ETZcocktails.CocktailList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailAPI {
    @GET("search.php?s=")
    fun fetchACock(@Query("s") CockToSearch:String) : Call<CocktailList?>?

}
