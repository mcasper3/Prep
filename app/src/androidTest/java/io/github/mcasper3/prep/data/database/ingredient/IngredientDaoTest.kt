package io.github.mcasper3.prep.data.database.ingredient

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.db.SupportSQLiteQueryBuilder
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import io.github.mcasper3.prep.data.database.step.Step
import io.github.mcasper3.prep.data.database.step.StepDao
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class IngredientDaoTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val recipe = Recipe(0, "Test RecipeListItem", "10 minutes", "2 minutes")
    private val step = Step(0, "Step 4", 4, 1)
    private val ingredient = Ingredient(0, 10, 1, "Ingredient", 1)

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
    fun verifyStoringIngredientSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)
        ingredientDao.insertAll(ingredient)

        val cursor = db.query(SupportSQLiteQueryBuilder.builder("ingredients").create())

        Assert.assertEquals(cursor.count, 1)

        cursor.moveToFirst()
        Assert.assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("name")), "Ingredient")
        Assert.assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow("amount")), 10)
        Assert.assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow("unit")), 1)
        Assert.assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow("step_id")), 1)
    }

    @Test
    fun verifyUpdateAllSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)
        ingredientDao.insertAll(ingredient)
        ingredientDao.updateAll(Ingredient(1, 14, 3, "Updated", 1))

        val expected = listOf(
            Ingredient(1, 14, 3, "Updated", 1)
        )

        ingredientDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun verifyDeleteSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)
        ingredientDao.insertAll(ingredient)
        ingredientDao.delete(Ingredient(1, 10, 1, "Ingredient", 1))

        val expected = listOf<Ingredient>()

        ingredientDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }
}
