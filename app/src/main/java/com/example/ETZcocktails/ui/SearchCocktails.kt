package com.example.ETZcocktails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailList
import com.example.ETZcocktails.R
import com.example.ETZcocktails.data.models.RetrofitHelper
import com.example.ETZcocktails.utils.autoCleared
import com.example.ETZcocktails.ui.all_characters.CocktailAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [SearchCocktails.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchCocktails : Fragment() {
    private var binding : SearchCocktails by autoCleared()

    //private val viewModel: cocktail_view_list by viewModels()

    private lateinit var  adapter: CocktailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
        }


            }

    fun getCocktails(CockToSearch :String){
        val API = RetrofitHelper.fetchACock(CockToSearch)
        API?.enqueue(object: Callback<CocktailList?> {
            override fun onResponse(
                call: Call<CocktailList?>,
                response: Response<CocktailList?>
            ) {
                if (response.body() != null) {
                    val cocktailList = response.body()!!.drinks!!
                    printCocktails(cocktailList)
                    for (cocktail in cocktailList) {
                        println(cocktail.strDrink)
                    }
                }
            }

            override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    TODO("Not yet implemented")
                println("error")
            }

        })


    }

    fun printCocktails(cocktailList: List<Cocktail>) {
        adapter = CocktailAdapter(cocktailList)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_search_cocktails, container, false)

        view.findViewById<Button>(R.id.searchButton).setOnClickListener {//Search for cocktails
            getCocktails(view.findViewById<EditText>(R.id.CocktailToSearchInput).text.toString())
        }

        return view
    }

    companion object{
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchCocktails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchCocktails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}