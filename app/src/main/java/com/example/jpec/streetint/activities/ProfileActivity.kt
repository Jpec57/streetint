package com.example.jpec.streetint.activities

import android.app.Activity
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jpec.streetint.R
import com.example.jpec.streetint.models.ProfileViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.jpec.streetint.models.ProfileDataModel


class ProfileActivity: AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        //observeProfile()
    }
/*
    private fun observeProfile() {
        profileViewModel.getFollowStatus().observe(this, object : Observer<ProfileDataModel.GenericInfo>() {
            fun onChanged(followStatus: FollowStatus) {
                toggleButton.setBackground(followStatus.buttonColor)
                toggleButton.setText(followStatus.buttonText)
                followersText.setText(followStatus.numberOfFollowers + " Followers")
            }
        })
    }
*/
}