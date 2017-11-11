package io.github.mcasper3.prep.recipes.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.github.mcasper3.prep.data.api.FailureUiModel
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.lang.RuntimeException

class RecipeListPresenterTest {

    private val recipeDataSource = mock<RecipeDataSource>()

    private lateinit var presenter: RecipeListPresenter

    @Before
    fun setUp() {
        presenter = RecipeListPresenter(recipeDataSource)
    }

    @Test
    fun verifySuccessUiModelIsReturnedWhenDataIsLoaded() {
        val recipeListItems = listOf(
            RecipeListItem("First", "100", "20"),
            RecipeListItem("Second", "15", "30")
        )
        whenever(recipeDataSource.getAllRecipes())
            .thenReturn(Flowable.just(recipeListItems))

        presenter.getRecipes()
            .test()
            .assertNoErrors()
            .assertValue(GetRecipeListSuccessUiModel(recipeListItems))
    }

    @Test
    fun verifySuccessUiModelIsReturnedWithEmptyListWhenNoDataIsLoaded() {
        whenever(recipeDataSource.getAllRecipes())
            .thenReturn(Flowable.just(emptyList()))

        presenter.getRecipes()
            .test()
            .assertNoErrors()
            .assertValue(GetRecipeListSuccessUiModel(emptyList()))
    }

    @Test
    fun verifyFailureUiModelIsReturnedWhenExceptionOccurs() {
        whenever(recipeDataSource.getAllRecipes())
            .thenReturn(Flowable.error(RuntimeException()))

        presenter.getRecipes()
            .test()
            .assertNoErrors()
            .assertValue(FailureUiModel())
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpScheduler() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        }
    }
}
