package io.github.mcasper3.prep.data.database.recipe

import io.github.mcasper3.prep.data.sources.RecipeDataSource
import io.reactivex.Observable
import okhttp3.MultipartBody

class RecipeDao: RecipeDataSource {
    override fun parseRecipe(image: MultipartBody.Part): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}