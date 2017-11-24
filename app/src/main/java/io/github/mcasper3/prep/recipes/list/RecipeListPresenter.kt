package io.github.mcasper3.prep.recipes.list

import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.base.FailureUiModel
import io.github.mcasper3.prep.base.UiModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RecipeListPresenter @Inject constructor(
    private val recipeDataSource: RecipeDataSource
) : Presenter<RecipeListView>() {

    fun getRecipes(): Flowable<UiModel> =
        recipeDataSource.getAllRecipes()
            .flatMap { Flowable.just<UiModel>(GetRecipeListSuccessUiModel(it.map { RecipeListViewHolderFactory(it) })) }
            .onErrorReturn {
                Timber.e(it)
                FailureUiModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
