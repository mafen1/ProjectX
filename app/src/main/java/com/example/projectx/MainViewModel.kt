package com.example.projectx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.sqrt

class MainViewModel : ViewModel() {

    var number1: MutableLiveData<Double> = MutableLiveData<Double>()
    var number2: MutableLiveData<Double> = MutableLiveData<Double>()
    var operations: MutableLiveData<String> = MutableLiveData("")
    var result: MutableLiveData<Double> = MutableLiveData(0.0)
    var resultList: MutableLiveData<MutableList<String>> =
        MutableLiveData<MutableList<String>>(mutableListOf())
    var isClickable: MutableLiveData<Boolean> = MutableLiveData(false)

    fun operations() {
        when (operations.value) {
            "+" -> result.value = number1.value!! + number2.value!!
            "-" -> result.value = number1.value!! - number2.value!!
            "*" -> result.value = number1.value!! * number2.value!!
            "/" -> result.value = number1.value!! / number2.value!!
            "//" -> result.value = sqrt(number1.value!!.toDouble())
            "%" -> result.value = (number1.value!! % 100)
            "**" -> result.value = number1.value!!.toDouble().pow(2.0)
            "/1" -> result.value = (1 / number1.value!!)
        }
        isClickable.value = false
    }

    fun clearAll() {
        number1.value = 0.0
        number2.value = 0.0
        operations.value = ""
        result.value = 0.0
        resultList.value = mutableListOf()
        isClickable.value = true
    }

    fun addHistoryResult() {
        val currentList = resultList.value ?: mutableListOf()
        currentList.add(result.value.toString())
        resultList.value = currentList
    }

}