package com.example.ETZcocktails.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentSearchCocktailsBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment
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
                    //printCocktails(cocktailList) // error so comment
                    for (cocktail in cocktailList) {
                        println(cocktail.strDrink)
                    }
                    binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
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
                else
                {
                    Toast.makeText(requireContext(),
                        "Error No Drinks Found!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(
                        "ETZ-CocktailsList-null",
                        "List Length is null"
                    )
                    //println("Error Drinks NULL!")
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                // TODO("Not yet implemented")
                //println("API onFailure ERROR")
                Log.d(
                    "ETZ-Cocktails-API-ERROR",
                    "Found No Cocktails on API"
                )
            }
        })
    }


    // error so comment
//    fun printCocktails(cocktailList: List<Cocktail>) {
//        adapter = CocktailAdapter(cocktailList)
//        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
//        binding.charactersRv.adapter = adapter
//    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchCocktailsBinding.inflate(inflater,container,false)
        // TODO InputValidation
        binding.searchButton.setOnClickListener {
            GetCocktailsByName(binding.CocktailToSearchInput.text.toString())
            view?.let { activity?.hideKeyboard(it) }
        }

        binding.CocktailToSearchInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                GetCocktailsByName(binding.CocktailToSearchInput.text.toString())
                return@OnKeyListener true
            }
            false
        })

        return binding.root
    }
}