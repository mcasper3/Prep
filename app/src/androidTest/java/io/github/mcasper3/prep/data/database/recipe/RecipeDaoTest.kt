package io.github.mcasper3.prep.data.database.recipe

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.db.SupportSQLiteQueryBuilder
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.database.ingredient.Ingredient
import io.github.mcasper3.prep.data.database.ingredient.IngredientDao
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepDao
import io.github.mcasper3.prep.data.database.step.StepWithIngredients
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val recipes = arrayOf(
        Recipe(name = "Test Recipe", cookTime = "10 minutes", prepTime = "2 minutes"),
        Recipe(name = "Second Test Recipe", cookTime = "8 minutes", prepTime = "1 minute")
    )
    private val steps = arrayOf(
        Step(text = "Step 1", stepNumber = 1, recipeId = 1),
        Step(text = "Step 2", stepNumber = 2, recipeId = 1)
    )
    private val ingredients = arrayOf(
        Ingredient(amount = 2, unit = 1, name = "Flour", stepId = 1),
        Ingredient(amount = 1, unit = 2, name = "Yeast", stepId = 1),
        Ingredient(amount = 4, unit = 0, name = "Eggs", stepId = 1),
        Ingredient(amount = 1, unit = 1, name = "Sugar", stepId = 2)
    )

    private lateinit var db: PrepDatabase
    private lateinit var recipeDao: RecipeDao
    private lateinit var stepDao: StepDao
    private lateinit var ingredientDao: IngredientDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            PrepDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        recipeDao = db.recipeDao()
        stepDao = db.stepDao()
        ingredientDao = db.ingredientDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun verifyStoringRecipeSucceeds() {
        recipeDao.insertAll(*recipes)

        val cursor = db.query(SupportSQLiteQueryBuilder.builder("recipes").create())

        assertEquals(cursor.count, 2)

        cursor.moveToFirst()
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("name")), "Test Recipe")
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("cook_time")), "10 minutes")
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("prep_time")), "2 minutes")
        cursor.moveToNext()
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("name")), "Second Test Recipe")
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("cook_time")), "8 minutes")
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("prep_time")), "1 minute")
    }

    @Test
    fun verifyGetAllSucceeds() {
        recipeDao.insertAll(*recipes)

        val expected = listOf(
            Recipe(1, "Test Recipe", "10 minutes", "2 minutes"),
            Recipe(2, "Second Test Recipe", "8 minutes", "1 minute")
        )

        recipeDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test fun verifyGetAllReturnsEmptyListWhenNoRecipesAreSaved() {
        recipeDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(emptyList())
    }

    @Test
    fun verifyGetByIdSucceeds() {
        recipeDao.insertAll(*recipes)
        stepDao.insertAll(*steps)
        ingredientDao.insertAll(*ingredients)

        val expected = RecipeWithSteps(1, "Test Recipe", "10 minutes", "2 minutes").apply {
            steps = listOf(
                StepWithIngredients(1, "Step 1", 1, 1).apply {
                    ingredients = listOf(
                        Ingredient(1, 2, 1, "Flour", 1),
                        Ingredient(2, 1, 2, "Yeast", 1),
                        Ingredient(3, 4, 0, "Eggs", 1)
                    )
                },
                StepWithIngredients(2, "Step 2", 2, 1).apply {
                    ingredients = listOf(
                        Ingredient(4, 1, 1, "Sugar", 2)
                    )
                }
            )
        }

        recipeDao.getById(1)
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun verifyUpdateAllSucceeds() {
        recipeDao.insertAll(*recipes)
        recipeDao.updateAll(Recipe(2, "Updated Recipe", "8 minutes", "2 minutes"))

        val expected = listOf(
            Recipe(1, "Test Recipe", "10 minutes", "2 minutes"),
            Recipe(2, "Updated Recipe", "8 minutes", "2 minutes")
        )

        recipeDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun verifyDeleteSucceeds() {
        recipeDao.insertAll(*recipes)
        recipeDao.delete(Recipe(1, "Test Recipe", "10 minutes", "2 minutes"))

        val expected = listOf(
            Recipe(2, "Second Test Recipe", "8 minutes", "1 minute")
        )

        recipeDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }
}
