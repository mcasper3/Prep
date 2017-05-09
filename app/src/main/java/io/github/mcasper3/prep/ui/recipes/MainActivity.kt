package io.github.mcasper3.prep.ui.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.bindView
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), RecipeView {
    val mAddRecipeButton: FloatingActionButton by bindView(R.id.add_recipe)
    val mRecipesList: RecyclerView by bindView(R.id.recipe_list)
    val mToolbar: Toolbar by bindView(R.id.toolbar)

    @Inject lateinit var mRecipeAdapter: RecipeAdapter
    @Inject lateinit var mPresenter: RecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent().inject(this)

        mPresenter.attachView(this)

        mToolbar.setTitle(R.string.title_activity_main)

        mRecipesList.adapter = mRecipeAdapter
        mRecipesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecipesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        mPresenter.getRecipes()
    }

    override fun onDestroy() {
        super.onDestroy()

        mPresenter.detachView()
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

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRecipes(recipes: Array<Recipe>) {
        mRecipeAdapter.setRecipes(recipes)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
