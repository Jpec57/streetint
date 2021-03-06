package com.example.jpec.streetint.fragments.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jpec.streetint.R
import com.example.jpec.streetint.activities.ChooseProgramActivity
import kotlinx.android.synthetic.main.fragment_main_activity.*

class MainFragment : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allworkouts.setOnClickListener {
            startActivity(Intent(activity!!.applicationContext, ChooseProgramActivity::class.java))
        }
    }

}