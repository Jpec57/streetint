package com.example.jpec.streetint.databases

import androidx.room.*
import android.content.Context
import com.example.jpec.streetint.models.IntMapConverter
import com.example.jpec.streetint.models.ProfileDataModel

@Dao
interface SkillUserInfoDao
{
    @Query("SELECT * from skills")
    fun getSkillInfo() : ProfileDataModel.SkillLevels

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSkillInfo(skills : ProfileDataModel.SkillLevels)
}

@Database(entities = [ProfileDataModel.SkillLevels::class], version = 1)
@TypeConverters(IntMapConverter::class)
abstract class SkillUserInfoDatabase : RoomDatabase()
{
    abstract fun skillUserInfoDao() : SkillUserInfoDao

    companion object {
        private var INSTANCE: SkillUserInfoDatabase? = null

        fun getInstance(context: Context): SkillUserInfoDatabase? {
            if (INSTANCE == null)
            {
                synchronized(SkillUserInfoDatabase::class)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SkillUserInfoDatabase::class.java, "skills.db").build()
                }

            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}