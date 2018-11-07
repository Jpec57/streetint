package com.example.jpec.streetint.interfaces

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import com.example.jpec.streetint.models.ExerciseListConverter
import com.example.jpec.streetint.models.ListConverter
import com.example.jpec.streetint.models.Workout

@Dao
interface WorkoutDAO {

    @Query("SELECT * from workouts")
    fun getAll(): List<Workout>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: Workout)

    @Query("DELETE from workouts where name = :name")
    fun delete(name: String)

    @Query("DELETE from workouts")
    fun deleteAll()
}


@Database(entities = [Workout::class], version = 1)
@TypeConverters(ListConverter::class, ExerciseListConverter::class)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDAO

    companion object {
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context): WorkoutDatabase? {
            if (INSTANCE == null) {
                synchronized(WorkoutDatabase::class) {
                    //tmp
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, WorkoutDatabase::class.java).build()

//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                        WorkoutDatabase::class.java, "workouts.db")
//                        .build()

                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }

}