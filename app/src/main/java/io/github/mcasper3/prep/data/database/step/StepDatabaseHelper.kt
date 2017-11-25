package io.github.mcasper3.prep.data.database.step

import javax.inject.Inject

class StepDatabaseHelper @Inject constructor(
    private val stepDao: StepDao
) {

    internal fun updateRecipeId(recipeId: Long) = stepDao.getAllByRecipeId(1)
        .flatMapIterable { it }
        .map { Step(it.id, it.text, it.stepNumber, recipeId) }
        .toList()
        .toFlowable()
        .subscribe { stepDao.insertAll(*it.toTypedArray()) }

    fun insertStep(step: io.github.mcasper3.prep.recipeviewer.Step) {
//        val stepToInsert = Step(text = "", stepNumber = step.stepNumber, recipeId = step.recipeId)
    }
}
