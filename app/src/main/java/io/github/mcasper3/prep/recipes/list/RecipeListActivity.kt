package io.github.mcasper3.prep.recipes.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.data.api.FailureUiModel
import io.github.mcasper3.prep.recipes.list.creation.CreationOptionsBottomSheetDialogFragment
import kotterknife.bindView
import javax.inject.Inject

class RecipeListActivity : PrepActivity<RecipeListPresenter, RecipeListView>(), RecipeListView {
    private val addRecipeButton: FloatingActionButton by bindView(R.id.add_recipe)
    private val recipesList: RecyclerView by bindView(R.id.recipe_list)
    private val toolbar: Toolbar by bindView(R.id.toolbar)

    @Inject lateinit var recipeAdapter: RecipeAdapter
    @Inject override lateinit var presenter: RecipeListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.title_activity_main)

        recipesList.adapter = recipeAdapter
        recipesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recipesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // TODO fancy RxJava stuff where we update the view directly from this method?
        presenter.getRecipes()

        addRecipeButton.setOnClickListener {
            CreationOptionsBottomSheetDialogFragment().show(supportFragmentManager, "creationOptions")
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(uiModel: FailureUiModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecipes(recipes: Array<Recipe>) {
        recipeAdapter.recipes = recipes
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, RecipeListActivity::class.java)
    }
}
