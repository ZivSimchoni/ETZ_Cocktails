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
import com.example.ETZcocktails.Cocktail
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

                GetRandomCocktails()
                try{
                    binding.DiceButton.setOnClickListener(View.OnClickListener {
                        GetRandomCocktails()
                    })
                }
                catch (e:Exception) {
                    Toast.makeText(
                        requireContext(),
                        "No internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                Toast.makeText(requireContext(),
                    "No internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                //hide RandomCocktailIntro
                binding.RandomCocktailIntro.visibility = View.GONE
                binding.DiceButton.visibility = View.GONE
            }

        }

        return binding.root
    }
    
    fun GetRandomCocktails() {
        val cocktailList: MutableList<Cocktail> = mutableListOf()
        for (i in 1..3) {
            val API = RetrofitHelper.FetchRandomCocktail()
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
                    binding.ListOfRandomCocktails.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                        override fun onItemClicked(index: Int) {
                            println("Clicked")
                            //replace fragment search cocktails with single cocktail
                            replaceFragment(SingleCocktailFragment(cocktailList[index]))
//                            val fragmentManager = parentFragmentManager
//                            val fragmentTransaction = fragmentManager.beginTransaction()
//                            fragmentTransaction.replace(R.id.frameLayout, SingleCocktailFragment(cocktailList[index])).addToBackStack(null).commit()

                        }

                        override fun onItemLongClicked(index: Int) {
                            println("Long Clicked")
                        }
                    }, false)
                    binding.ListOfRandomCocktails.layoutManager = LinearLayoutManager(requireContext())
                }

                override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    TODO("Not yet implemented")
                    println("API onFailure ERROR")
                }
            })
        }
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