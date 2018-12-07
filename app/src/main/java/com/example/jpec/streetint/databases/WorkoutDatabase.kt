package com.example.jpec.streetint.databases

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import com.example.jpec.streetint.models.ExerciseListConverter
import com.example.jpec.streetint.models.ListConverter
import com.example.jpec.streetint.models.Workout

@Dao
interface WorkoutDAO {

    @Query("SELECT * from premadeWorkouts")
    fun getAllWorkouts(): List<Workout>

    @Query("SELECT * from premadeWorkouts where name =:name")
    fun getWorkout(name: String) : List<Workout>

    @Query("SELECT * from premadeWorkouts where name =:name LIMIT :limit")
    fun getWorkoutWithLimit(name: String, limit: Int) : List<Workout>

    @Query("SELECT * from premadeWorkouts where name=:name order by timestamp limit 1")
    fun getInitWorkout(name: String) : Workout

    @Query("SELECT w1.* FROM premadeWorkouts w1 INNER JOIN (SELECT DISTINCT name, timestamp FROM premadeWorkouts WHERE saved = 1 ORDER BY timestamp LIMIT 1) AS w2 ON w1.name = w2.name AND w1.timestamp = w2.timestamp")
    fun getSavedWorkouts() : List<Workout>

    @Insert(onConflict = REPLACE)
    fun insertWorkout(workout: Workout)

    @Query("DELETE from premadeWorkouts where name = :name")
    fun deleteWorkout(name: String)

    @Query("DELETE from premadeWorkouts")
    fun deleteAllWorkouts()
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
//                    INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, WorkoutDatabase::class.java).build()

                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WorkoutDatabase::class.java, "premadeWorkouts.db")
                        .build()

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
    var ready = false

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
        this.ready = true
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }

}