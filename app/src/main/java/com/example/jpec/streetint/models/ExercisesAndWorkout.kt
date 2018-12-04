package com.example.jpec.streetint.models

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.IndexOutOfBoundsException
import kotlin.collections.ArrayList

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "bodyweight") val bodyweight: Boolean = true,
    @ColumnInfo(name = "img")val img: Int = 0,
    @ColumnInfo(name = "description") val description: String = "Not available",
    @TypeConverters(ListConverter::class) @ColumnInfo(name = "material") val material: ArrayList<String> = arrayListOf("None"),
    @TypeConverters(ListConverter::class) @ColumnInfo(name = "muscles") val muscles: ArrayList<String> = arrayListOf("Unknown"),
    @ColumnInfo(name = "isStatic")val isStatic: Boolean = false,
    @ColumnInfo(name = "rest") var rest: Int = 90,
    @ColumnInfo(name = "reps") var reps: Int = 5,
    @ColumnInfo(name = "series") var series: Int = 5,
    @ColumnInfo(name = "weight") var weight: Int = 0,
    @ColumnInfo(name = "tempo") var tempo: Int = 2,
    @ColumnInfo(name = "difficulty")val difficulty: Float = 0.0f,
    @ColumnInfo(name = "superset")var superset: Exercise? = null
) : Serializable

fun getDifferentMusclesOrMaterial(exo: MutableList<Exercise>,
                                  isMuscle : Boolean = true) : ArrayList<String>
{
    lateinit var musclesExo : ArrayList<String>
    lateinit var differentMuscles : ArrayList<String>
    try {
        if (exo.isEmpty())
            return arrayListOf("None")
        musclesExo = if (isMuscle) { exo[0].muscles } else exo[0].material
        differentMuscles = musclesExo
        for (e in exo)
        {
            musclesExo = if (isMuscle) e.muscles else e.material
            for (i in musclesExo.indices)
            {
                if (!differentMuscles.contains(musclesExo[i]))
                {
                    differentMuscles.add(musclesExo[i])
                }
            }
        }
    }catch (e : IndexOutOfBoundsException)
    {
        return arrayListOf("None")
    }
    return (differentMuscles)
}

fun computeTimeForWorkout(exo: MutableList<Exercise>): Int
{
    var totalEstimatedTime = 0
    var exoEstimatedTime: Int
    try {
        if (exo.isEmpty())
            return 0
        for (e in exo)
        {
            exoEstimatedTime = e.rest * (e.series - 1) + e.tempo * e.reps
            if (e.superset != null)
            {
                e.superset!!.let {
                    exoEstimatedTime += it.tempo * it.reps
                }
            }
            totalEstimatedTime += exoEstimatedTime
        }
    }
    catch (e: IndexOutOfBoundsException)
    {
        return 0
    }
    return (totalEstimatedTime)
}

@Entity(tableName = "workouts", primaryKeys = ["timestamp", "name"])
data class Workout(
    @ColumnInfo(name = "timestamp") var timestamp: Long = 0L,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "img") var img: Int = 0,
    @ColumnInfo(name = "cycle") var cycle: Boolean = false,
    @ColumnInfo(name = "saved") var saved: Boolean = false,
    @ColumnInfo(name = "description") var description: String = "No description given",
    @TypeConverters(ExerciseListConverter::class) @ColumnInfo(name = "exercises") var exercises: ArrayList<Exercise>,
    @TypeConverters(ListConverter::class) @ColumnInfo(name = "material") var material: ArrayList<String> = getDifferentMusclesOrMaterial(
        exercises,
        false
    ),
    @TypeConverters(ListConverter::class) @ColumnInfo(name = "muscles") var muscles: ArrayList<String> = getDifferentMusclesOrMaterial(
        exercises
    ),
    @ColumnInfo(name = "time") var time: Int = computeTimeForWorkout(exercises)
) : Serializable {
    constructor() : this(0L, "None", 0, false,false, "Empty", arrayListOf<Exercise>(Exercise("Empty")))
}


class ExerciseListConverter : Serializable {
    @TypeConverter
    fun fromExerciseList(exerciseList: ArrayList<Exercise>?) : String
    {
        if (exerciseList == null)
        {
            return ""
        }
        val gson = Gson()
        val type = object: TypeToken<ArrayList<Exercise>>(){}.type
        val json = gson.toJson(exerciseList, type)
        return json
    }

    @TypeConverter
    fun toExerciseList(exerciseListAsString: String?) : ArrayList<Exercise>?
    {
        if (exerciseListAsString == null)
        {
            return null
        }
        val gson = Gson()
        val type = object: TypeToken<ArrayList<Exercise>>(){}.type
        return gson.fromJson(exerciseListAsString, type)
    }
}

data class StringList(val list: MutableList<String> = mutableListOf("None")) : Serializable

class ListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toStringList(value: String?): ArrayList<String> {
            if (value == null || value.isEmpty()) {
                return ArrayList()
            }

            val list: List<String> = value.split(",")
            val stringList = arrayListOf<String>()
            for (item in list) {
                if (!item.isEmpty()) {
                    stringList.add(item)
                }
            }
            return stringList
        }

        @TypeConverter
        @JvmStatic
        fun toString(list: ArrayList<String>?): String {

            var string = ""

            if (list == null) {
                return string
            }

            list.forEach {
                string += "$it,"
            }
            return string
        }
    }
}