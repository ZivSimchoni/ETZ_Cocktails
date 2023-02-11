package com.example.ETZcocktails.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.databinding.FragmentFavCocktailsBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment
import com.example.ETZcocktails.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FavCocktails : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_fav_cocktails, container, false)
//    }

    private var _binding : FragmentFavCocktailsBinding? = null

    val viewModel : CocktailViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavCocktailsBinding.inflate(inflater,container,false)
        val cocktailList=viewModel.getListFavoriteCocktails()!!
        if (!cocktailList.isEmpty())
        {

            //get cocktail list
            try
            {

                //show all cocktails
                binding.NoCocktailsAdded.visibility = View.GONE

                binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                    override fun onItemClicked(index: Int) {
                        println("Clicked")
                        replaceFragment(SingleCocktailFragment(cocktailList[index]))
//                        val fragmentManager = parentFragmentManager
//                        val fragmentTransaction = fragmentManager.beginTransaction()
//                        fragmentTransaction.replace(R.id.frameLayout, SingleCocktailFragment(cocktailList[index])).addToBackStack(null).commit()
                    }
                    override fun onItemLongClicked(index: Int) {
                        println("Long Clicked")
                    }
                },true,viewModel)
                binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())

            }
            catch (e:Exception)
            {

                print("error in cocktail list probably")
            }
        }
        else{
            binding.NoCocktailsAdded.visibility = View.VISIBLE
            if(GlobalFunctions().isOnline(requireContext())) {
                GetRandomCocktail()
            }
            else {
                Toast.makeText(requireContext(),
                    "No internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
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
                    binding.ListOfRandomCocktails.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
                            //replace fragment search cocktails with single cocktail
                            replaceFragment(SingleCocktailFragment(cocktailList[index]))
//                            val fragmentManager = parentFragmentManager
//                            val fragmentTransaction = fragmentManager.beginTransaction()
//                            fragmentTransaction.replace(R.id.frameLayout, SingleCocktailFragment(cocktailList[index])).addToBackStack("SingleViewCocktail").commit()
                        }

                        override fun onItemLongClicked(index: Int) {
                            println("Long Clicked")
                        }
                    }, false)
                    binding.ListOfRandomCocktails.layoutManager = LinearLayoutManager(requireContext())
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
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                Toast.makeText(requireContext(),
                    "Server Error connection failed!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "ETZ-Cocktails-API-ERROR",
                    "Found No Cocktails on API"
                )
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_down,R.anim.slide_up)
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.addToBackStack("favCocktails")
        fragmentTransaction.commit()
    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.addToBackStack("favCocktails")
//        fragmentTransaction.commit()
//    }




}