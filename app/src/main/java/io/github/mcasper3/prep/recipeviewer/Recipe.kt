package io.github.mcasper3.prep.recipeviewer

class Recipe(
    val steps: MutableList<Step>,
    var name: String,
    var cookTime: String,
    var prepTime: String
) {

    operator fun plus(steps: List<Step>) {
        this.steps.addAll(steps)
    }

    operator fun plus(step: Step) {
        steps.add(step)
    }
}

class Step(
    val text: String,
    val ingredients: List<Ingredient>
) {

}

class Ingredient(
    val amount: Int,
    val unit: Int,
    val name: String
) {

}
