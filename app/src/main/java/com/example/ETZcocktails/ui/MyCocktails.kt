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
import androidx.recyclerview.widget.RecyclerView
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.databinding.FragmentMyCocktailsBinding
import com.example.ETZcocktails.ui.add_cocktail.AddCocktail
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import com.example.ETZcocktails.ui.single_cocktail.SingleCocktailFragment

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
            //todo : need to find out how to refresh in order to use this
            var listLen = viewModel.getListCocktailsByMe()!!.toList().size
            if (listLen == 0) {
                Toast.makeText(binding.root.context,
                    "First make at least one Cocktail!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
            viewModel.deleteMyCocktails() // only this are the old
            //binding.root.removeAllViews() // only this are the old
            binding.root.findViewById<RecyclerView>(R.id.CocktailViewList).removeAllViewsInLayout()
            Log.d(
                "ETZ-Delete-ALL-Cocktail",
                "Deleted All Cocktails."
            )
            Toast.makeText(binding.root.context,
                "Deleted All Cocktails!",
                Toast.LENGTH_SHORT
            ).show()
            }
        }

        if (viewModel.items != null)
        {
            //TODO show all cocktails - this is a temp value
            //binding.textMyCocktailName.text = viewModel.items.toString()
            //get cocktail list
            try
            {
                val cocktailList=viewModel.getListCocktailsByMe()!!
                //show all cocktails
                binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                    override fun onItemClicked(index: Int) {
                        println("Clicked")
                        val fragmentManager = parentFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frameLayout, SingleCocktailFragment(cocktailList[index])).addToBackStack(null).commit()
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
        fragmentTransaction.addToBackStack("myCocktails")
        fragmentTransaction.commit()
    }
}