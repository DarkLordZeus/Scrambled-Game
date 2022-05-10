package com.example.scrambled

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

class numbers {
    companion object {
        var currentnum=MutableLiveData<Int>(1)
        var scorecard=MutableLiveData<Int>(0)
        var MaxVal=MutableLiveData<Int>(10)


    }
}