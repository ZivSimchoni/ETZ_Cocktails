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
            try //to get cocktail list
            {
                //show all cocktails
                binding.NoCocktailsAdded.visibility = View.GONE
                binding.scrollView.visibility = View.GONE
                binding.CocktailViewList.adapter = CocktailAdapter(cocktailList, object : CocktailAdapter.ItemListener {
                    override fun onItemClicked(index: Int) {
                        replaceFragment(SingleCocktailFragment(cocktailList[index]))
                    }
                    override fun onItemLongClicked(index: Int) {
                    }
                },true,viewModel)
                binding.CocktailViewList.layoutManager = LinearLayoutManager(requireContext())

            }
            catch (e:Exception)
            {
                Toast.makeText(requireContext(),
                    R.string.api_fail_message,
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(
                    "ETZ-List-Favorites-Cocktails-Failure",
                    "error in cocktail list probably"
                )
            }
        }
        else{
            binding.scrollView.visibility = View.VISIBLE
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
                        R.string.no_connection_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                Toast.makeText(requireContext(),
                    R.string.no_connection_message,
                    Toast.LENGTH_SHORT
                ).show()
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
                            replaceFragment(SingleCocktailFragment(cocktailList[index]))
                        }
                        override fun onItemLongClicked(index: Int) {
                        }
                    }, false)
                    binding.ListOfRandomCocktails.layoutManager = LinearLayoutManager(requireContext())
                }

                override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    Toast.makeText(requireContext(),
                        R.string.api_fail_message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(
                        "ETZ-Cocktails-API-ERROR-Fav",
                        "Found No Cocktails on API"
                    )
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
}