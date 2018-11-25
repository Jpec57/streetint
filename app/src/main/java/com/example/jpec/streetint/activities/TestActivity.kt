package com.example.jpec.streetint.activities


import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.jpec.streetint.R
import com.example.jpec.streetint.utils.ObservableList
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : Activity() {
    private val TAG = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
}