package io.github.mcasper3.prep.add

import io.github.mcasper3.prep.base.InProgressUiModel
import io.github.mcasper3.prep.base.Presenter
import io.github.mcasper3.prep.base.UiModel
import io.github.mcasper3.prep.data.database.recipe.RecipeDatabaseHelper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddRecipePresenter @Inject constructor(
    private val recipeDatabaseHelper: RecipeDatabaseHelper
) : Presenter<AddRecipeView>() {

    fun saveRecipe(recipeInformation: RecipeInformation): Flowable<UiModel> {
        return recipeDatabaseHelper.insertRecipe(recipeInformation)
            .toFlowable()
            .map(Function<io.github.mcasper3.prep.data.database.recipe.Recipe, UiModel> { RecipeSaveSuccessUiModel() })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(InProgressUiModel())
    }
}
