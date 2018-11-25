package com.example.jpec.streetint.interfaces

import android.arch.lifecycle.LiveData
import com.example.jpec.streetint.models.ProfileDataModel

interface IProfileSkills {
    fun getSkillLevels() : LiveData<ProfileDataModel.SkillLevels>
}