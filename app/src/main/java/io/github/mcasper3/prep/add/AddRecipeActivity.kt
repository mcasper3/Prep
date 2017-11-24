package io.github.mcasper3.prep.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.PrepRecyclerActivity

class AddRecipeActivity : PrepRecyclerActivity<AddRecipePresenter, AddRecipeView>(), AddRecipeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_standard_recycler)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_recipe, menu)
        return true
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, AddRecipeActivity::class.java)
    }
}
