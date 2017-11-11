package io.github.mcasper3.prep.recipes.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class RecipeDataSourceTest {

    private val recipeDao = mock<RecipeDao>()

    private lateinit var dataSource: RecipeDataSource

    @Before
    fun setUp() {
        dataSource = RecipeDataSource(recipeDao)
    }

    @Test
    fun verifyDataSourceReturnsCorrectlyMappedRecipes() {
        whenever(recipeDao.getAll())
            .thenReturn(
                Flowable.just(
                    listOf(
                        Recipe(1, "First", "100", "20"),
                        Recipe(2, "Second", "15", "30")
                    )
                )
            )

        dataSource.getAllRecipes()
            .test()
            .assertNoErrors()
            .assertValue(
                listOf(
                    RecipeListItem("First", "100", "20"),
                    RecipeListItem("Second", "15", "30")
                )
            )
    }

    @Test
    fun verifyEmptyListIsReturnedWhenDaoReturnsNoItems() {
        whenever(recipeDao.getAll())
            .thenReturn(Flowable.just(emptyList()))

        dataSource.getAllRecipes()
            .test()
            .assertNoErrors()
            .assertValue(emptyList())
    }
}
