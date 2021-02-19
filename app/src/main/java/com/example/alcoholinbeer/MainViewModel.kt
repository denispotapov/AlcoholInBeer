package com.example.alcoholinbeer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class MainViewModel : ViewModel() {

    val initialDensity = MutableLiveData<Double?>()
    val finalDensity = MutableLiveData<Double?>()

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun onResultButtonClick() {
       if (initialDensity.value !=null && finalDensity.value !=null) {
            _result.value = "Алкоголь объемный: ${getAlcohol(initialDensity.value, finalDensity.value)}%"
       }

        if (initialDensity.value == null && finalDensity.value == null) {
            _message.value = "Введите плотность"
        }

        if (initialDensity.value == null && finalDensity.value != null) {
            _message.value = "Введите начальную плотность"
        }

        if (initialDensity.value != null && finalDensity.value == null) {
            _message.value = "Введите конечную плотность"
        }
    }

    private fun getAlcohol(initialDensity: Double?, finalDensity: Double?): String {
        fun sG(brix: Double?): Double {
            var result = 0.0
            if (brix != null) {
                result = (brix / (258.6 - ((brix / 258.2) * 227.1))) + 1
            }
            return result
        }

        val abv =
            (76.08 * (sG(initialDensity) - sG(finalDensity)) / (1.775 - sG(initialDensity))) * (sG(finalDensity) / 0.794)
        return DecimalFormat("0.00").format(abv)
    }
}
