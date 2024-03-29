package io.github.mcasper3.prep.recipes.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.mcasper3.prep.R
import javax.inject.Inject

class RecipeAdapter @Inject constructor() : RecyclerView.Adapter<RecipeViewHolder>() {

    internal var recipes: List<RecipeListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(recipes[position])

    override fun getItemCount() = recipes.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
        RecipeViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_recipe, parent, false))
}
