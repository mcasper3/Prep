package io.github.mcasper3.prep.recipeviewer.part

import android.os.Bundle
import android.support.v4.app.Fragment

class RecipePartFragment : Fragment() {

    fun something() {

    }

    companion object {
        private const val RECIPE_PART = "arg_recipePart"

        fun newInstance(recipePart: RecipePart) =
                RecipePartFragment().apply { arguments = Bundle().apply { putParcelable(RECIPE_PART, recipePart) } }
    }

}
