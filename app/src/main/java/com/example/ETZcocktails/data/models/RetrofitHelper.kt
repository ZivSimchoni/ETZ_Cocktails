package com.example.ETZcocktails.data.models

import com.example.ETZcocktails.CocktailList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    var cocktailapi: CocktailAPI
    val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"

    init {
        var retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        cocktailapi = retrofit.create(CocktailAPI::class.java)
    }
    fun fetchACock(CockToSearch: String): Call<CocktailList?>?{
        return cocktailapi.fetchACock(CockToSearch)
    }
}