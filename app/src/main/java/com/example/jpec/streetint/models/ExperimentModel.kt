package com.example.jpec.streetint.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.net.URL
import java.nio.charset.Charset

class ExperimentModel(): ViewModel()
{
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _mutableLiveDataExercises = MutableLiveData<String>()

    /**
     * Request a exercises_data_live to display a string.
     *
     * Use Transformations.map to wrap each string sent to _snackbar in a non-null value
     */
    val exercises_data_live: LiveData<String>
        get() = _mutableLiveDataExercises


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onMainViewClicked() {
        // launch a coroutine in uiScope

        uiScope.launch {
            // suspend this coroutine for one second
            delay(1_000)

//            val str = URL("https://www.whatismyip.com/").readText(Charset.forName("UTF-8"))
//            _mutableLiveDataExercises.value = str
            _mutableLiveDataExercises.value = "Hello, from coroutines!"


        }
    }

    fun onSnackbarShown() {
        _mutableLiveDataExercises.value = null
    }
}