package com.example.ETZcocktails.ui.all_characters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.CocktailViewModel
import com.example.ETZcocktails.R
import com.example.ETZcocktails.databinding.CocktailViewListBinding


class CocktailAdapter(val items:List<Cocktail>,val callBack: ItemListener, val TrashVisibility:Boolean =true,val viewModel: CocktailViewModel?=null)
    : RecyclerView.Adapter<CocktailAdapter.ItemViewHolder>() {

    interface ItemListener {
        fun onItemClicked(index:Int)
        fun onItemLongClicked(index:Int)
    }

    inner class ItemViewHolder(private val binding: CocktailViewListBinding)
        : RecyclerView.ViewHolder(binding.root),View.OnClickListener,
        View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            callBack.onItemClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            callBack.onItemLongClicked(adapterPosition)
            return false
        }

        fun bind(cocktail: Cocktail) {
            binding.NameOfCocktail.text = cocktail.strDrink
            binding.NumberOfIngredients.text = getNumberOfIng(cocktail).toString()
            binding.CocktailInstructions.text = getInstructionPreview(cocktail.strInstructions.toString())
            binding.Trash.visibility= if (TrashVisibility) View.VISIBLE else View.INVISIBLE

            Glide.with(binding.root).load(cocktail.strDrinkThumb).circleCrop()
                .into(binding.PhotoOfCocktail)
            binding.PhotoOfCocktail

            if(TrashVisibility && viewModel!=null)
            {
                binding.Trash.setOnClickListener{
                    Log.d(
                        "ETZ-Delete-Cocktail",
                        "Cocktail Deleted ${cocktail.strDrink.toString()}"
                    )
                    viewModel.deleteItem(cocktail)
                    binding.root.removeAllViews()
                    Toast.makeText(binding.root.context,
                        R.string.deleted_message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getNumberOfIng(cocktail : Cocktail) :Int
    {
        var numberOfIng = 0
        if (cocktail.strIngredient1 != null&&cocktail.strIngredient1 !="") numberOfIng++
        if (cocktail.strIngredient2 != null&&cocktail.strIngredient2 !="") numberOfIng++
        if (cocktail.strIngredient3 != null&&cocktail.strIngredient3 !="") numberOfIng++
        if (cocktail.strIngredient4 != null&&cocktail.strIngredient4 !="") numberOfIng++
        if (cocktail.strIngredient5 != null&&cocktail.strIngredient5 !="") numberOfIng++
        return numberOfIng
    }

    private fun getInstructionPreview(fullInstructions: String): String
    {
        if (fullInstructions.length > 30)
        {
            return (fullInstructions.substring(0,30) + "...")
        }
        return fullInstructions
    }

    fun itemAt(position:Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(CocktailViewListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() =
        items.size
}