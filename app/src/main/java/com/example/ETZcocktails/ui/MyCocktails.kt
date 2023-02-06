package com.example.ETZcocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.MainActivity
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.repository.CocktailRepository
import com.example.ETZcocktails.databinding.FragmentMyCocktailsBinding
import com.example.ETZcocktails.ui.add_cocktail.AddCocktail
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyCocktails : Fragment() {

    private var _binding : FragmentMyCocktailsBinding? = null

    val viewModel : CocktailViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyCocktailsBinding.inflate(inflater,container,false)

        binding.btnAddCocktail.setOnClickListener{
            replaceFragment(AddCocktail())
        }

        binding.btnDeleteCocktail.setOnClickListener{
            //TODO delete all DB
        }

        if (viewModel.items != null)
        {
            //TODO show all cocktails - this is a temp value
            binding.textMyCocktailName.text = viewModel.items.toString()
            //get cocktail list
            val cocktailList=viewModel.getListCocktails()!!
            //show all cocktails
            binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                override fun onItemClicked(index: Int) {
                    println("Clicked")
                }

                override fun onItemLongClicked(index: Int) {
                    println("Long Clicked")
                }
            })
            binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())
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
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.addToBackStack("myCocktails")
        fragmentTransaction.commit()
    }
}