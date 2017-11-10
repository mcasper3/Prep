package io.github.mcasper3.prep.data.database.step

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.db.SupportSQLiteQueryBuilder
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import io.github.mcasper3.prep.data.database.PrepDatabase
import io.github.mcasper3.prep.data.database.recipe.Recipe
import io.github.mcasper3.prep.data.database.recipe.RecipeDao
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StepDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val recipe = Recipe(0, "Test RecipeListItem", "10 minutes", "2 minutes")
    private val step = Step(0, "Step 4", 4, 1)

    private lateinit var db: PrepDatabase
    private lateinit var recipeDao: RecipeDao
    private lateinit var stepDao: StepDao

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
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun verifyStoringStepSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)

        val cursor = db.query(SupportSQLiteQueryBuilder.builder("steps").create())

        Assert.assertEquals(cursor.count, 1)

        cursor.moveToFirst()
        Assert.assertEquals(cursor.getString(cursor.getColumnIndexOrThrow("text")), "Step 4")
        Assert.assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow("step_number")), 4)
        Assert.assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow("recipe_id")), 1)
    }

    @Test
    fun verifyUpdateAllSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)
        stepDao.updateAll(Step(1, "Updated Step", 2, 1))

        val expected = listOf(
            Step(1, "Updated Step", 2, 1)
        )

        stepDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }

    @Test
    fun verifyDeleteSucceeds() {
        recipeDao.insertAll(recipe)
        stepDao.insertAll(step)
        stepDao.delete(Step(1, "Step 4", 4, 1))

        val expected = listOf<Step>()

        stepDao.getAll()
            .test()
            .assertNoErrors()
            .assertValue(expected)
    }
}
