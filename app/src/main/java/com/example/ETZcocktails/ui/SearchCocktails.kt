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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentSearchCocktailsBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment
import com.example.ETZcocktails.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCocktails : Fragment() {

    private var _binding : FragmentSearchCocktailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var  adapter: CocktailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //get the cocktail that correspond to the name sent
    fun GetCocktailsByName(CocktailToSearch :String){
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
                    binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
                            var bundle:Bundle = Bundle()
                            bundle.putInt("id", cocktailList[index].id!!)
                            bundle.putLong("idDrink", cocktailList[index].idDrink!!)
                            bundle.putString("name", cocktailList[index].strDrink)
                            bundle.putString("image", cocktailList[index].strDrinkThumb)
                            bundle.putString("instructions", cocktailList[index].strInstructions)
                            bundle.putString("ingredients", cocktailList[index].strIngredient1)
                            bundle.putString("ingredients2", cocktailList[index].strIngredient2)
                            bundle.putString("ingredients3", cocktailList[index].strIngredient3)
                            bundle.putString("ingredients4", cocktailList[index].strIngredient4)
                            bundle.putString("ingredients5", cocktailList[index].strIngredient5)
                            bundle.putString("measure", cocktailList[index].strMeasure1)
                            bundle.putString("measure2", cocktailList[index].strMeasure2)
                            bundle.putString("measure3", cocktailList[index].strMeasure3)
                            bundle.putString("measure4", cocktailList[index].strMeasure4)
                            bundle.putString("measure5", cocktailList[index].strMeasure5)

                            replaceFragment(SingleCocktailFragment().newInstance(bundle))
                        }

                        override fun onItemLongClicked(index: Int) {
                        }
                    }, false)
                    binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())
                }
                else
                {
                    Toast.makeText(requireContext(),
                        R.string.no_cocktails_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(
                        "ETZ-CocktailsList-null",
                        "List Length is null"
                    )
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                Toast.makeText(requireContext(),
                    R.string.api_fail_message,
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "ETZ-Cocktails-API-ERROR",
                    "Found No Cocktails on API"
                )
            }
        })
    }

    fun GetRandomCocktail(){
        val API = RetrofitHelper.FetchRandomCocktail()
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
                    binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
                            var bundle:Bundle = Bundle()
                            bundle.putInt("id", cocktailList[index].id!!)
                            bundle.putLong("idDrink", cocktailList[index].idDrink!!)
                            bundle.putString("name", cocktailList[index].strDrink)
                            bundle.putString("image", cocktailList[index].strDrinkThumb)
                            bundle.putString("instructions", cocktailList[index].strInstructions)
                            bundle.putString("ingredients", cocktailList[index].strIngredient1)
                            bundle.putString("ingredients2", cocktailList[index].strIngredient2)
                            bundle.putString("ingredients3", cocktailList[index].strIngredient3)
                            bundle.putString("ingredients4", cocktailList[index].strIngredient4)
                            bundle.putString("ingredients5", cocktailList[index].strIngredient5)
                            bundle.putString("measure", cocktailList[index].strMeasure1)
                            bundle.putString("measure2", cocktailList[index].strMeasure2)
                            bundle.putString("measure3", cocktailList[index].strMeasure3)
                            bundle.putString("measure4", cocktailList[index].strMeasure4)
                            bundle.putString("measure5", cocktailList[index].strMeasure5)

                            replaceFragment(SingleCocktailFragment().newInstance(bundle))
                        }

                        override fun onItemLongClicked(index: Int) {
                            //TODO: remove its useless
                            println("Long Clicked")
                        }
                    }, false)
                    binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())
                }
                else
                {
                    Toast.makeText(requireContext(),
                        R.string.no_cocktails_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(
                        "ETZ-CocktailsList-null",
                        "List Length is null"
                    )
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                Toast.makeText(requireContext(),
                    R.string.api_fail_message,
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "ETZ-Cocktails-API-ERROR",
                    "Found No Cocktails on API"
                )
            }
        })
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchCocktailsBinding.inflate(inflater,container,false)
        //when search button is clicked
        binding.searchButton.setOnClickListener {
            if(GlobalFunctions().isOnline(requireContext())) {
                GetCocktailsByName(binding.CocktailToSearchInput.text.toString())
                view?.let { activity?.hideKeyboard(it) }
            }
            else {
                Toast.makeText(requireContext(),
                    R.string.no_connection_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.randomButton.setOnClickListener{
            if(GlobalFunctions().isOnline(requireContext())) {
                GetRandomCocktail()
                view?.let { activity?.hideKeyboard(it) }
            }
            else {
                Toast.makeText(requireContext(),
                    R.string.no_connection_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.CocktailToSearchInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if(GlobalFunctions().isOnline(requireContext())) {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    GetCocktailsByName(binding.CocktailToSearchInput.text.toString())
                    return@OnKeyListener true
                }
            }
            else {
                Toast.makeText(requireContext(),
                    R.string.no_connection_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            false
        })

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.addToBackStack("SearchCocktails")
        fragmentTransaction.commit()
    }
}