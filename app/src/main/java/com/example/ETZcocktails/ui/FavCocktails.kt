package com.example.ETZcocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.databinding.FragmentFavCocktailsBinding
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment


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

        if (viewModel.items != null)
        {
            //get cocktail list
            try
            {
                val cocktailList=viewModel.getListFavoriteCocktails()!!
                //show all cocktails
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

        return binding.root
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