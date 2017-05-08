package io.github.mcasper3.recipevision.ui.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import butterknife.bindView
import io.github.mcasper3.recipevision.R

class MainActivity : AppCompatActivity() {
    val mAddRecipeButton: FloatingActionButton by bindView(R.id.add_recipe)
    val mRecipesLsit: RecyclerView by bindView(R.id.recipe_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
