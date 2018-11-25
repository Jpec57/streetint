package com.example.jpec.streetint.utils

import android.content.Context
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.io.File
import java.io.FileOutputStream
import java.net.URL

fun String.saveTo(path: String) {
    val input = URL(this).openStream()
    val output = FileOutputStream(File(path))
    input.use {
        output.use {
            input.copyTo(output)
        }
    }
}

class ObservableList<T>
{
    private var list = ArrayList<T>()
    private var onAdd : PublishSubject<T> = PublishSubject.create()

    fun add(value : T)
    {
        list.add(value)
        onAdd.onNext(value)
    }
    fun getObservable() = onAdd
}


/*
    val test = ObservableList<String>()
            test.getObservable().subscribe(System.out::println)

 */