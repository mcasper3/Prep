package io.github.mcasper3.prep.recipes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.BaseViewHolder
import io.github.mcasper3.prep.base.recycler.ViewHolderFactory

class RecipeListViewHolderFactory(item: RecipeListItem) : ViewHolderFactory<RecipeListItem>(item) {

    override fun createViewHolder(parent: ViewGroup) =
        RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false))

    override fun bind(viewHolder: BaseViewHolder<RecipeListItem>) {
        viewHolder.bind(item)
    }
}
