package com.example.ETZcocktails.ui.single_cocktail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.databinding.FragmentSingleCocktailBinding



class SingleCocktailFragment(cocktail: Cocktail) : Fragment() {

    private var binding:FragmentSingleCocktailBinding ?= null

    private val viewModel: CocktailViewModel by viewModels()
    val cocktailToDisplay : Cocktail = cocktail
    private var imageUri: Uri? = null

    val pickImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            binding?.singleCocktailImage?.let {view->
                Glide.with(requireContext()).load(it).circleCrop().into(
                    view
                )
            }
            binding?.singleCocktailImage?.visibility = View.VISIBLE
            requireActivity().contentResolver.takePersistableUriPermission(it!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            imageUri = it
        }


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
    fun selectradio(cocktail: Cocktail){
        when(cocktail.strAlcoholic!!)
        {
            binding?.root?.findViewById<RadioButton>(binding?.radioAlcoholic!!.id)?.hint.toString() -> binding?.singleCocktailRadioGroup?.check(binding?.radioAlcoholic!!.id)
            binding?.root?.findViewById<RadioButton>(binding?.radioNonAlcoholic!!.id)?.hint.toString() -> binding?.singleCocktailRadioGroup?.check(binding?.radioNonAlcoholic!!.id)
            binding?.root?.findViewById<RadioButton>(binding?.radioOptionalAlcoholic!!.id)?.hint.toString() -> binding?.singleCocktailRadioGroup?.check(binding?.radioOptionalAlcoholic!!.id)
            else -> binding?.singleCocktailRadioGroup?.check(binding?.radioAlcoholic!!.id)
        }
    }

    private fun updateCocktail(cocktail: Cocktail) {

        displayCocktail(cocktail)
        var newCocktail: Cocktail = cocktail.copy()
        var Arr = arrayOf(Pair(binding?.singleCocktailName,"strDrink"),Pair(binding?.singleCocktailInstructions,"strInstructions"),Pair(binding?.singleCocktailIng1,"strIngredient1"),Pair(binding?.singleCocktailIng2,"strIngredient2"),Pair(binding?.singleCocktailIng3,"strIngredient3"),Pair(binding?.singleCocktailIng4,"strIngredient4"),Pair(binding?.singleCocktailIng5,"strIngredient5"),Pair(binding?.singleCocktailIng1Value,"strMeasure1"),Pair(binding?.singleCocktailIng2Value,"strMeasure2"),Pair(binding?.singleCocktailIng3Value,"strMeasure3"),Pair(binding?.singleCocktailIng4Value,"strMeasure4"),Pair(binding?.singleCocktailIng5Value,cocktail.strMeasure5))

            selectradio(cocktail)

        binding?.EditButton?.visibility = View.INVISIBLE
        //check if the cocktail is inside the db or not. If it is, then hide the add button
        //if cocktail already liked, display liked image
        if (viewModel.getItemIdDrink((cocktail.idDrink)!!) != null) {
            binding?.Fav?.setImageResource(R.drawable.ic_star_rate)
        } else {
            //if cocktail not liked, display not liked image
            binding?.Fav?.setImageResource(R.drawable.ic_star_rate_blank)
        }
        //Click on the image to add the cocktail to the View-list
        binding?.Fav?.setOnClickListener {
            //if cocktail is liked, dislike it
            if (viewModel.getItemIdDrink((cocktail.idDrink)!!) != null) {
                if(cocktail.idDrink!!<=-1) //if cocktail is made by me
                {
                    Toast.makeText(requireContext(),
                        R.string.removed_my_cocktails_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else //if cocktail is from api
                {
                    Toast.makeText(requireContext(),
                        R.string.removed_favs_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                viewModel.deleteItemIdDrink(cocktail.idDrink!!)
                binding?.Fav?.setImageResource(R.drawable.ic_star_rate_blank)
            }
            else { //if cocktail is not liked , like it
                if(cocktail.idDrink!!<=-1) //if cocktail is made by me
                {
                    Toast.makeText(requireContext(),
                        R.string.added_my_cocktails_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else //if cocktail is from api
                {
                    Toast.makeText(requireContext(),
                        R.string.added_favs_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                viewModel.addFavItem(cocktail)
                binding?.Fav?.setImageResource(R.drawable.ic_star_rate)
            }
        }

        //if cocktail is type MyCocktail


        if(cocktail.idDrink!! < 0){
            val animshake = AnimationUtils.loadAnimation(requireContext(),R.anim.shake_text)
            binding?.singleCocktailImage?.setImageDrawable(getDrawable(requireContext(),R.drawable.ic_launcher))


            //set onclick listener for edit button and when clicked animate all first elemnts in arr
            binding?.EditButton?.visibility = View.VISIBLE

            binding?.EditButton?.setOnClickListener{
                binding?.radioAlcoholic?.isClickable = true
                binding?.radioNonAlcoholic?.isClickable = true
                binding?.radioOptionalAlcoholic?.isClickable = true
                Arr.forEach{
                    it.first?.isClickable = true
                }
                binding?.singleCocktailImage?.isClickable = true


                binding?.EditButton?.visibility = View.GONE
                binding?.Fav?.visibility = View.GONE
                binding?.cancelButton?.visibility = View.VISIBLE
                binding?.saveButton?.visibility = View.VISIBLE

                binding?.saveButton?.setOnClickListener{

                    if (imageUri != null){
                        newCocktail.strDrinkThumb = imageUri.toString()
                    }
                    val selectedId: Int = binding?.singleCocktailRadioGroup?.checkedRadioButtonId!!
                    newCocktail.strAlcoholic = binding?.root?.findViewById<RadioButton>(selectedId)?.hint.toString()

                    viewModel.updateCocktail(newCocktail)
                    Toast.makeText(requireContext(),
                        R.string.edited_message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Arr.forEach { it.first?.clearAnimation()
                    }
                    binding?.EditButton?.visibility = View.VISIBLE
                    binding?.Fav?.visibility = View.VISIBLE
                    binding?.cancelButton?.visibility = View.GONE
                    binding?.saveButton?.visibility = View.GONE
                    Arr.forEach{
                        it.first?.isClickable = false
                    }
                    binding?.singleCocktailImage?.clearAnimation()
                    binding?.singleCocktailImage?.isClickable = false
                    binding?.radioAlcoholic?.isClickable = false
                    binding?.radioNonAlcoholic?.isClickable = false
                    binding?.radioOptionalAlcoholic?.isClickable = false
                }
                binding?.cancelButton?.setOnClickListener{
                    selectradio(cocktail)
                    Toast.makeText(requireContext(),
                        "Canceled", //TODO: add string
                        Toast.LENGTH_SHORT
                    ).show()
                    Arr.forEach { it.first?.clearAnimation()
                    }
                    binding?.EditButton?.visibility = View.VISIBLE
                    binding?.Fav?.visibility = View.VISIBLE
                    binding?.cancelButton?.visibility = View.GONE
                    binding?.saveButton?.visibility = View.GONE
                    displayCocktail(cocktail)
                    Arr.forEach{
                        it.first?.isClickable = false
                    }
                    binding?.singleCocktailImage?.clearAnimation()
                    binding?.singleCocktailImage?.isClickable = false
                    binding?.radioAlcoholic?.isClickable = false
                    binding?.radioNonAlcoholic?.isClickable = false
                    binding?.radioOptionalAlcoholic?.isClickable = false

                }


                if (cocktail.strDrinkThumb == "null") {
                    binding?.singleCocktailImage?.isClickable = false
                }
                else {
                    binding?.singleCocktailImage?.startAnimation(animshake)
                    binding?.singleCocktailImage?.setOnClickListener {
                        pickImageLauncher.launch(arrayOf("image/*"))
                    }
                }


                Arr.forEach {
                    it.first?.startAnimation(animshake)
                    it.first?.setOnClickListener{_->
                        val arrayListCollection: ArrayList<CharSequence> = ArrayList()
                        var adapter: ArrayAdapter<CharSequence?>
                        var txt: EditText
                        val alertName: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                        val editTextName1 = EditText(requireContext())
                        editTextName1.maxLines = 1
                        alertName.setTitle("${getString(R.string.edit_message)} ${it.second}")
                        alertName.setView(editTextName1)
                        val layoutName = LinearLayout(requireContext())
                        layoutName.orientation = LinearLayout.VERTICAL
                        layoutName.addView(editTextName1)
                        alertName.setView(layoutName)
                        alertName.setPositiveButton(R.string.save_message) { _, _ ->
                            val name = editTextName1.text.toString()
                            if (name != "") {
                                newCocktail.setByColumn(it.second!!, name)
                                it.first?.text = name


                            }
                            else {
                                Toast.makeText(requireContext(),
                                    R.string.value_missing_message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        alertName.setNegativeButton(R.string.cancel_message) { dialog, _ ->
                            dialog.cancel() // Canceled.
                        }
                        alertName.show()
                    }}
            }



            Arr.forEach {  }
            //for each element in the array setonclicklistener to display an alert dialog
            binding?.singleCocktailImage?.let {
                Glide.with(requireContext()).load(cocktail.strDrinkThumb).circleCrop().into(
                    it
                )
            }
        }

    }
    fun displayCocktail(cocktail: Cocktail){
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
        binding?.singleCocktailImage?.let {
            Glide.with(requireContext()).load(cocktail.strDrinkThumb).circleCrop().into(
                it
            )
        }
    }
}