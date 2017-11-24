package io.github.mcasper3.prep.data.database.step

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface StepDao {
    @Query("SELECT * FROM steps")
    fun getAll(): Flowable<List<Step>>

    @Insert
    fun insertAll(vararg steps: Step): List<Long>

    @Insert
    fun insert(step: Step): Long

    @Update
    fun updateAll(vararg steps: Step)

    @Delete
    fun delete(steps: Step)
}
