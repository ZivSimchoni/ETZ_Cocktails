package com.example.ETZcocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentSearchIngredientBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchIngredient : Fragment() {

    private var _binding : FragmentSearchIngredientBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchIngredientBinding.inflate(inflater,container,false)
        // TODO InputValidation
        binding.searchButton.setOnClickListener {
            GetCocktailsByIng(binding.enterIngName.text.toString())
        }

        return binding.root
    }

    fun GetCocktailsByIng(IngToSearch :String){
        val API = RetrofitHelper.FetchCocktailByIng(IngToSearch)
        API?.enqueue(object: Callback<CocktailList?> {
            override fun onResponse(
                call: Call<CocktailList?>,
                response: Response<CocktailList?>
            ) {

                if (response.body() != null) {
                    //get list of cocktail names containing the ingredient
                    val cocktailList = response.body()!!.drinks!!

                    for (cocktail in cocktailList) {

                        //send each cocktail name and print its name (in the future display it)
                        GetCocktailsByName(cocktail.strDrink.toString())
                    }
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                // TODO("Not yet implemented")
                println("API onFailure ERROR")
            }
        })
    }

    fun GetCocktailsByName(CocktailToSearch :String){
        // TODO: re-implement this function - null safety
        val API = RetrofitHelper.FetchCocktailByName(CocktailToSearch)
        API?.enqueue(object: Callback<CocktailList?> {
            override fun onResponse(
                call: Call<CocktailList?>,
                response: Response<CocktailList?>
            ) {
                if (response.body()?.drinks != null) {
                    val cocktailList = response.body()!!.drinks!!

                    for (cocktail in cocktailList) {
                        println(cocktail.strDrink)
                    }
                }
                else
                {
                    println("error Drinks NULL!")
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                TODO("Not yet implemented")
                println("API onFailure ERROR")
            }
        })
    }


}