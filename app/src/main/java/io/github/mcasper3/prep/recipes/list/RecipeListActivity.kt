package io.github.mcasper3.prep.recipes.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.base.recycler.PrepRecyclerActivity
import io.github.mcasper3.prep.base.FailureUiModel
import io.github.mcasper3.prep.base.UiModel
import io.github.mcasper3.prep.recipes.list.creation.CreationOptionsBottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable

class RecipeListActivity : PrepRecyclerActivity<RecipeListPresenter, RecipeListView>(), RecipeListView {

    private val compositeDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_standard_recycler)

        toolbar?.setTitle(R.string.title_activity_main)
        emptyStateImage?.setImageResource(R.drawable.empty_state)
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

    override fun onSwipeRefresh() {
        getRecipes()
    }

    override fun onFabClicked() {
        CreationOptionsBottomSheetDialogFragment().show(supportFragmentManager, "creationOptions")
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

    private fun showRecipes(recipes: List<RecipeListViewHolderFactory>) {
        hideEmpty()
        adapter.viewHolderFactories = recipes
        hideLoading()
    }

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, RecipeListActivity::class.java)
    }
}
