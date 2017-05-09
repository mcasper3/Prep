package io.github.mcasper3.prep.ui.recipes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.mcasper3.prep.R
import javax.inject.Inject

class RecipeAdapter @Inject constructor() : RecyclerView.Adapter<RecipeViewHolder>() {
    private var mRecipes: Array<Recipe> = emptyArray()

    fun setRecipes(recipes: Array<Recipe>) {
        mRecipes = recipes

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        mRecipes[position].bind(holder)
    }

    override fun getItemCount(): Int {
        return mRecipes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_recipe, parent, false))
    }
}