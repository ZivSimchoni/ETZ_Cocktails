package com.example.ETZcocktails.ui.single_cocktail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.ETZcocktails.databinding.FragmentSingleCocktailBinding
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.utils.autoCleared
import com.example.ETZcocktails.utils.Loading
import com.example.ETZcocktails.utils.Success
import com.example.ETZcocktails.utils.Error


class SingleCocktailFragment(cocktail: Cocktail) : Fragment() {

    private var binding:FragmentSingleCocktailBinding ?= null

    private val viewModel: CocktailViewModel by viewModels()
    val cocktailToDisplay : Cocktail = cocktail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleCocktailBinding.inflate(inflater, container, false)
        updateCocktail(cocktailToDisplay)

        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        updateCocktail(cocktailToDisplay)
    }





    private fun updateCocktail(cocktail: Cocktail) {

        println(cocktail.id)
        binding?.singleCocktailName?.text = cocktail.strDrink
        binding?.singleCocktailInstructions?.text = cocktail.strInstructions
        binding?.singleCocktailIng1?.text = cocktail.strIngredient1
        binding?.singleCocktailIng1Value?.text = cocktail.strMeasure1
        binding?.singleCocktailIng2?.text = cocktail.strIngredient2
        binding?.singleCocktailIng2Value?.text = cocktail.strMeasure2
        binding?.singleCocktailIng3?.text = cocktail.strIngredient3
        binding?.singleCocktailIng3Value?.text = cocktail.strMeasure3
        binding?.singleCocktailIng4?.text = cocktail.strIngredient4
        binding?.singleCocktailIng4Value?.text = cocktail.strMeasure4
        binding?.singleCocktailIng5?.text = cocktail.strIngredient5
        binding?.singleCocktailIng5Value?.text = cocktail.strMeasure5
        //check if the cocktail is inside the db or not. If it is, then hide the add button





        if (viewModel.getItemIdDrink((cocktail.idDrink)!!) != null) {
            binding?.Fav?.setImageResource(R.drawable.ic_favorite_24)


        } else {
            binding?.Fav?.setImageResource(R.drawable.ic_send)


            //set on click listner to add the cocktail to the viewlist
        }
        binding?.Fav?.setOnClickListener {
            if (viewModel.getItemIdDrink((cocktail.idDrink)!!) != null) {
                println("removed")
                viewModel.deleteItemIdDrink(cocktail.idDrink!!)
                binding?.Fav?.setImageResource(R.drawable.ic_send)
            }
            else{
                println("added")
                viewModel.addFavItem(cocktail)
                binding?.Fav?.setImageResource(R.drawable.ic_favorite_24)
            }
        }



        binding?.singleCocktailImage?.let {
            Glide.with(requireContext()).load(cocktail.strDrinkThumb).circleCrop().into(
                it
            )
        }
    }
}