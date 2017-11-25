package io.github.mcasper3.prep.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import io.github.mcasper3.prep.R
import io.github.mcasper3.prep.add.step.AddStepActivity
import io.github.mcasper3.prep.base.InProgressUiModel
import io.github.mcasper3.prep.base.UiModel
import io.github.mcasper3.prep.base.recycler.PrepRecyclerActivity
import io.reactivex.disposables.CompositeDisposable
import kotterknife.bindView

class AddRecipeActivity : PrepRecyclerActivity<AddRecipePresenter, AddRecipeView>(), AddRecipeView {

    private val recipeName: EditText by bindView(R.id.recipe_name)
    private val totalPrepTime: EditText by bindView(R.id.total_prep_time)
    private val totalCookTime: EditText by bindView(R.id.total_cook_time)
    private val addStepButton: View by bindView(R.id.add_step)

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_recycler_with_top_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addStepButton.setOnClickListener { startActivity(AddStepActivity.createIntent(this, adapter.itemCount + 1)) }
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                saveRecipe()
                onBackPressed()
            }
            R.id.save -> saveRecipe(false)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveRecipe(isAutoSave: Boolean = true) {
        compositeDisposable.add(
            presenter
                .saveRecipe(
                    RecipeInformation(recipeName.text.toString(), totalCookTime.text.toString(), totalPrepTime.text.toString())
                )
                .subscribe { if (!isAutoSave) updateUi(it) }
        )
    }

    private fun updateUi(uiModel: UiModel) {
        when (uiModel) {
            is InProgressUiModel -> showLoading()
            is RecipeSaveSuccessUiModel -> {
                hideLoading()
                onBackPressed()
            }
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, AddRecipeActivity::class.java)
    }
}
