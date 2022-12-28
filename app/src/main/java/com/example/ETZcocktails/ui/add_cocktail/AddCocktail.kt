package com.example.ETZcocktails.ui.add_cocktail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
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

        binding.finishBtn.setOnClickListener {

            val cocktail  = Cocktail(1, "A Cocktail","try for yourselves",
            "",
                "IBA","Nutz","Alcoholic","lowBall","leftB","rightB","it",
                "null","null","null","null","null","null",
                "null","null","null","null","null","null","null",
                "null","null","null","null", imageUri.toString())
            print("Cocktail Added:\n${cocktail}")
            viewModel.addItem(cocktail)

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