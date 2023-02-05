package com.example.ETZcocktails.ui.add_cocktail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.databinding.FragmentAddCocktailBinding


class AddCocktail : Fragment() {

    private var _binding : FragmentAddCocktailBinding? = null

    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val viewModel : CocktailViewModel by activityViewModels()

    val pickImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            binding.resultImage.setImageURI(it)
            requireActivity().contentResolver.takePersistableUriPermission(it!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            imageUri = it
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCocktailBinding.inflate(inflater,container,false)
        var index = 1
        binding.finishBtn.setOnClickListener {
            // TODO Input Validation!
//            val cocktail  = Cocktail(index, binding.addCocktailName.text.toString(), binding.addCocktailInstructions.text.toString(),
//            "","null","Nutz","Alcoholic","lowBall",
//                binding.addCocktailIngredient1.text.toString(),binding.addCocktailIngredient2.text.toString(),binding.addCocktailIngredient3.text.toString(),binding.addCocktailIngredient4.text.toString(),
//                binding.addCocktailIngredient5.text.toString(),"null","null","null","null","null",
//                binding.addCocktailIngredient1measure.text.toString(),binding.addCocktailIngredient2measure.text.toString(),binding.addCocktailIngredient3measure.text.toString(),binding.addCocktailIngredient4measure.text.toString(),
//                binding.addCocktailIngredient5measure.text.toString(),"null","null","null","null","null",
//                imageUri.toString())
            // get selected radio button from radioGroup
            // get selected radio button from radioGroup


            //find selected alcohol from radio button

            val selectedId: Int = binding.addCocktailRadioGroup.checkedRadioButtonId
            val radio_button_text: String=binding.root.findViewById<RadioButton>(selectedId).hint.toString()



            val cocktail = Cocktail(index,-1,binding.addCocktailName.text.toString(),radio_button_text,binding.addCocktailInstructions.text.toString(),imageUri.toString(),
                  binding.addCocktailIngredient1.text.toString(),binding.addCocktailIngredient2.text.toString(),binding.addCocktailIngredient3.text.toString(),binding.addCocktailIngredient4.text.toString(),binding.addCocktailIngredient5.text.toString(),
                  binding.addCocktailIngredient1measure.text.toString(),binding.addCocktailIngredient2measure.text.toString(),binding.addCocktailIngredient3measure.text.toString(),binding.addCocktailIngredient4measure.text.toString(),binding.addCocktailIngredient5measure.text.toString())
            print("Cocktail Added:\n${cocktail}")
            viewModel.addItem(cocktail)
            index ++

            //findNavController().navigate(R.id.action_myCocktails2_to_addCocktail)

            }

        binding.imageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*"))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}