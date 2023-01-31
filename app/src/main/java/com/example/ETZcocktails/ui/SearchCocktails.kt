package com.example.ETZcocktails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentSearchCocktailsBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchCocktails : Fragment() {

    private var _binding : FragmentSearchCocktailsBinding? = null

    private val binding get() = _binding!!

    //private val viewModel: cocktail_view_list by viewModels()

    private lateinit var  adapter: CocktailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun GetCocktailsByName(CockToSearch :String){
        // TODO: re-implement this function - null safety
        val API = RetrofitHelper.FetchCocktailByName(CockToSearch)
        API?.enqueue(object: Callback<CocktailList?> {
            override fun onResponse(
                call: Call<CocktailList?>,
                response: Response<CocktailList?>
            ) {
                if (response.body()?.drinks != null) {
                    val cocktailList = response.body()!!.drinks!!
                    //printCocktails(cocktailList) // error so comment
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
                println("error")
            }
        })
    }

    // error so comment
//    fun printCocktails(cocktailList: List<Cocktail>) {
//        adapter = CocktailAdapter(cocktailList)
//        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
//        binding.charactersRv.adapter = adapter
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchCocktailsBinding.inflate(inflater,container,false)
        // TODO InputValidation
        binding.searchButton.setOnClickListener {
            GetCocktailsByName(binding.CocktailToSearchInput.text.toString())
        }

        return binding.root
    }
}