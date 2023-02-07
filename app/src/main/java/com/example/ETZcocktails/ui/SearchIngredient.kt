package com.example.ETZcocktails.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentSearchIngredientBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment
import com.example.ETZcocktails.utils.GlobalFunctions
import kotlinx.coroutines.*
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
            if(GlobalFunctions().isOnline(requireContext()))
            {
                GetCocktailsByIng(binding.enterIngName.text.toString())
            }
            else
            {
                Toast.makeText(requireContext(),
                    "No internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }

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
                    val cocktailNameList: MutableList<String> = mutableListOf()
                    for (cocktail in cocktailList) {

                        cocktailNameList.add(cocktail.strDrink.toString())
                        //send each cocktail name and print its name (in the future display it)
                        //GetCocktailsByName(cocktail.strDrink.toString())
                    }

                    GetCocktailsByName(cocktailNameList)
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                // TODO("Not yet implemented")
                //println("API onFailure ERROR")
                Log.d(
                    "ETZ-Ingredient-List-null",
                    "List Length is null"
                )
            }
        })
    }



    fun GetCocktailsByName(CocktailsToSearch : MutableList<String>) {
        val cocktailList: MutableList<Cocktail> = mutableListOf()
        for (cocktail in CocktailsToSearch) {
            val API = RetrofitHelper.FetchCocktailByName(cocktail)
            API?.enqueue(object : Callback<CocktailList?> {
                override fun onResponse(
                    call: Call<CocktailList?>,
                    response: Response<CocktailList?>
                ) {
                    if (response.body()?.drinks != null) {
                        val cocktail_ret = response.body()!!.drinks!!
                        cocktailList.add(cocktail_ret[0])
                    }

                    //show all cocktails
                    binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
                            println("Clicked")
                            //replace fragment search cocktails with single cocktail
                            val fragmentManager = parentFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.frameLayout, SingleCocktailFragment(cocktailList[index])).addToBackStack(null).commit()

                        }

                        override fun onItemLongClicked(index: Int) {
                            println("Long Clicked")
                        }
                    }, false)
                    binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())
                }

                override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    TODO("Not yet implemented")
                    println("API onFailure ERROR")
                }
            })
        }
    }




}