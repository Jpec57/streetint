package com.example.jpec.streetint.models

import androidx.room.*
import com.example.jpec.streetint.constants.Constants
import com.example.jpec.streetint.utils.createHashMapFromKeysWithDefaultValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProfileDataModel {
    @Entity(tableName = "skills")
    data class SkillLevels(@PrimaryKey var username: String,
                           @ColumnInfo(name= "globalSkillLevel") var globalSkillLevel: Int = 0,
                           @TypeConverters(IntMapConverter::class)
                               @ColumnInfo(name= "skillLevels") var skillLevels: MutableMap<String, Int>
                               = createHashMapFromKeysWithDefaultValue(Constants.skills, 1),
                           @TypeConverters(IntMapConverter::class)
                               @ColumnInfo(name= "skillPercents") var skillPercents : MutableMap<String, Int>
                               = createHashMapFromKeysWithDefaultValue(Constants.skills, 0)
                           )

    class MuscleLevels(var globalMuscleLevel: Int = 0,
                       @TypeConverters(IntMapConverter::class)
                       var muscleLevels: MutableMap<String, Int>,
                       @TypeConverters(IntMapConverter::class)
                       var musclePercents : MutableMap<String, Int>)

    class GenericInfo(val username: String,
                      val gender: String = "Male",
                      var userIcon: String? = null,
                      var globalLvl: Int = 0,
                      var xp: Int = 0,
                      var weight : Float = 70.0f,
                      var height: Int = 178,
                      var age: Int = 21,
                      var goal: String = "Strength")
}

class IntMapConverter {
    @TypeConverter
    fun fromString(value: String): Map<String, Int> {
        val mapType = object : TypeToken<Map<String, Int>>() {

        }.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String, Int>): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}