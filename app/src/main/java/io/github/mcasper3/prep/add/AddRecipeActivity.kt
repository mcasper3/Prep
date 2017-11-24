package io.github.mcasper3.prep.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.widget.EditText
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.base.recycler.RecyclerAdapter
import kotterknife.bindView
import javax.inject.Inject

class AddRecipeActivity : PrepActivity<AddRecipePresenter, AddRecipeView>(), AddRecipeView {

    @Inject internal lateinit var adapter: RecyclerAdapter

    private val recyclerView: RecyclerView by bindView(R.id.steps_list)
    private val recipeName: EditText by bindView(R.id.recipe_name)
    private val totalPrepTime: EditText by bindView(R.id.total_prep_time)
    private val totalCookTime: EditText by bindView(R.id.total_cook_time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_recipe, menu)
        return true
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, AddRecipeActivity::class.java)
    }
}
