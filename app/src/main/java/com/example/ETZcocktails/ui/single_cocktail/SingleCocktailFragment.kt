package com.example.ETZcocktails.ui.single_cocktail

//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.view.isVisible
//import androidx.fragment.app.viewModels
////import com.example.ETZcocktails.databinding
//import com.bumptech.glide.Glide
//import com.example.ETZcocktails.Cocktail
//import com.example.ETZcocktails.utils.autoCleared
//import com.example.ETZcocktails.utils.Loading
//import com.example.ETZcocktails.utils.Success
//import com.example.ETZcocktails.utils.Error
//
//
//class SingleCocktailFragment : Fragment() {
//
//    private var binding: SingleCocktailFragment by autoCleared()
//
//    private val viewModel:SingleCocktailViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = .inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        // TODO: fix that shit, cannot get the 'it'
//        viewModel.singleCocktail.observe(viewLifecycleOwner) {
//            when(it.status) {
//                is Loading -> binding.progressBar.isVisible = true
//                is Success -> {
//                    binding.progressBar.isVisible = false
//                    updateCocktail(it.status.data!!)
//                }
//                is Error -> {
//                    binding.progressBar.isVisible = false
//                    Toast.makeText(requireContext(),it.status.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//        // TODO: do we need this:?
//        arguments?.getInt("id")?.let {
//            viewModel.setId(it)
//        }
//    }
//
//    private fun updateCocktail(cocktail: Cocktail) {
//        binding.singleCocktailName.text = cocktail.strDrink
//        binding.singleCocktailInstructions.text = cocktail.strInstructions
//        binding.singleCocktailIng1.text = cocktail.strIngredient1
//        binding.singleCocktailIng1Value.text = cocktail.strMeasure1
//        binding.singleCocktailIng2.text = cocktail.strIngredient2
//        binding.singleCocktailIng2Value.text = cocktail.strMeasure2
//        binding.singleCocktailIng3.text = cocktail.strIngredient3
//        binding.singleCocktailIng3Value.text = cocktail.strMeasure3
//        binding.singleCocktailIng4.text = cocktail.strIngredient4
//        binding.singleCocktailIng4Value.text = cocktail.strMeasure4
//        binding.singleCocktailIng5.text = cocktail.strIngredient5
//        binding.singleCocktailIng5Value.text = cocktail.strMeasure5
//
//        Glide.with(requireContext()).load(cocktail.strDrinkThumb).circleCrop().into(binding.singleCocktailImage)
//    }
//}