package com.example.jpec.streetint.interfaces

import androidx.lifecycle.LiveData
import com.example.jpec.streetint.models.ProfileDataModel

interface IProfileSkills {
    fun getSkillLevels() : LiveData<ProfileDataModel.SkillLevels>
}