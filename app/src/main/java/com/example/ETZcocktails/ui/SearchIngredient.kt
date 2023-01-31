package com.example.ETZcocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentAddCocktailBinding
import com.example.ETZcocktails.databinding.FragmentSearchIngredientBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ETZcocktails.utils.autoCleared
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter


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
        // Inflate the layout for this fragment
        _binding = FragmentSearchIngredientBinding.inflate(inflater,container,false)
        //val view: View = inflater.inflate(R.layout.fragment_search_ingredient, container, false)

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
                    val cocktailList = response.body()!!.drinks!!
                    for (cocktail in cocktailList) {
                        println(cocktail.strDrink)
                    }
                }
            }
            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                TODO("Not yet implemented")
                println("error")
            }
        })
    }

}