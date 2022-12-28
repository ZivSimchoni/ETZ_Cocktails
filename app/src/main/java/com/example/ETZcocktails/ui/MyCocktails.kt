package com.example.ETZcocktails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.MainActivity
import com.example.ETZcocktails.R
import com.example.ETZcocktails.databinding.FragmentMyCocktailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyCocktails : Fragment() {

    private var _binding : FragmentMyCocktailsBinding? = null

    private val binding get() = _binding!!

    val viewModel : CocktailViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyCocktailsBinding.inflate(inflater,container,false)

        binding.btnAddCocktail.setOnClickListener{
            val cocktail  = Cocktail(1, "A Cocktail","try for yourselves",
                "",
                "IBA","NutZ","Alcoholic","lowBall","leftB","rightB","it",
                "null","null","null","null","null","null",
                "null","null","null","null","null","null","null",
                "null","null","null","null", "imageUri.toString()")
            print("Cocktail #2 Added: ${cocktail.name}\n")
        }

//        binding.fab.setOnClickListener{
//            val cocktail  = Cocktail(1, "Ziv Cock's","try for yourselves",
//                "",
//                "IBA","Nutz","Alcoholic","lowBall","leftB","rightB","it",
//                "null","null","null","null","null","null",
//                "null","null","null","null","null","null","null",
//                "null","null","null","null", "imageUri.toString()")
//            print("Cocktail #2 Added: ${cocktail.name}\n")
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
//        setMenuVisibility(true)
        _binding = null
    }
}