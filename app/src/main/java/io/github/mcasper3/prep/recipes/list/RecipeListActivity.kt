package io.github.mcasper3.prep.recipes.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.PrepActivity
import io.github.mcasper3.prep.data.api.FailureUiModel
import io.github.mcasper3.prep.data.api.UiModel
import io.github.mcasper3.prep.recipes.list.creation.CreationOptionsBottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable
import kotterknife.bindView
import javax.inject.Inject

class RecipeListActivity : PrepActivity<RecipeListPresenter, RecipeListView>(), RecipeListView {

    @Inject lateinit var recipeAdapter: RecipeAdapter
    @Inject override lateinit var presenter: RecipeListPresenter

    private val addRecipeButton: FloatingActionButton by bindView(R.id.add_recipe)
    private val swipeRefreshLayout: SwipeRefreshLayout by bindView(R.id.recipe_refresh)
    private val recipesList: RecyclerView by bindView(R.id.recipe_list)
    private val toolbar: Toolbar by bindView(R.id.toolbar)
    private val loadingView: View by bindView(R.id.loading)
    private val emptyStateImage: View by bindView(R.id.empty_state_image)
    private val emptyStateText: TextView by bindView(R.id.empty_state_text)

    private val compositeDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.title_activity_main)

        recipesList.adapter = recipeAdapter
        recipesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recipesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        swipeRefreshLayout.setOnRefreshListener { getRecipes() }
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        loadingView.visibility = View.VISIBLE

        addRecipeButton.setOnClickListener {
            CreationOptionsBottomSheetDialogFragment().show(supportFragmentManager, "creationOptions")
        }
    }

    override fun onResume() {
        super.onResume()
        getRecipes()
    }

    override fun onPause() {
        compositeDisposables.dispose()
        super.onPause()
    }

    override fun showLoading() {
        // No-op
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmpty() {
        hideLoading()
        recipeAdapter.recipes = emptyList()
        recipeAdapter.notifyDataSetChanged()
        emptyStateImage.visibility = View.VISIBLE
        emptyStateText.visibility = View.VISIBLE
        emptyStateText.setText(R.string.no_recipes)
    }

    override fun showError(uiModel: FailureUiModel) {
        showEmpty()
        emptyStateText.setText(R.string.failed_to_load_recipes)
    }

    override fun updateUi(uiModel: UiModel) {
        when (uiModel) {
            is FailureUiModel -> showError(uiModel)
            is GetRecipeListSuccessUiModel -> when (uiModel.recipes.size) {
                0 -> showEmpty()
                else -> showRecipes(uiModel.recipes)
            }
        }
    }

    private fun getRecipes() = compositeDisposables.add(presenter.getRecipes().subscribe { updateUi(it) })

    private fun showRecipes(recipes: List<RecipeListItem>) {
        recipesList.visibility = View.VISIBLE
        emptyStateImage.visibility = View.GONE
        emptyStateText.visibility = View.GONE
        recipeAdapter.recipes = recipes
        recipeAdapter.notifyDataSetChanged()
        hideLoading()
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, RecipeListActivity::class.java)
    }
}
