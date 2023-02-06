package com.example.ETZcocktails.ui.all_characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ETZcocktails.Cocktail
import com.example.ETZcocktails.databinding.CocktailViewListBinding

class CocktailAdapter(val items:List<Cocktail>, val callBack: ItemListener)
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
            print("BINDED")
            binding.NameOfCocktail.text = cocktail.strDrink
            binding.NumberOfIngredients.text = "5"

            //binding.itemImage.setImageURI(Uri.parse(item.photo))
            Glide.with(binding.root).load(cocktail.strDrinkThumb).circleCrop()
                .into(binding.PhotoOfCocktail)
            binding.PhotoOfCocktail
        }
    }

    fun itemAt(position:Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(CocktailViewListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() =
        items.size

}