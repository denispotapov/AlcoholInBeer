package com.example.alcoholinbeer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class MainViewModel : ViewModel() {

    val initialDensity = MutableLiveData<String>()
    val finalDensity = MutableLiveData<String>()

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    fun onResultButtonClick() {
       if (!initialDensity.value.isNullOrEmpty() && !finalDensity.value.isNullOrEmpty()) {
            _result.value = "Алкоголь объемный: ${getAlcohol(initialDensity.value.toString(), finalDensity.value.toString())}%"
       }

        if (initialDensity.value.isNullOrEmpty() && finalDensity.value.isNullOrEmpty()) {
            _message.value = "Введите плотность"
        }

        if (initialDensity.value.isNullOrEmpty() && !finalDensity.value.isNullOrEmpty()) {
            _message.value = "Введите начальную плотность"
        }

        if (!initialDensity.value.isNullOrEmpty() && finalDensity.value.isNullOrEmpty()) {
            _message.value = "Введите конечную плотность"
        }
    }

    private fun getAlcohol(initialDensity: String, finalDensity: String): String {
        fun sG(brix: String): Double {
            return (brix.toDouble() / (258.6 - ((brix.toDouble() / 258.2) *
                    227.1))) + 1
        }

        val abv =
            (76.08 * (sG(initialDensity) - sG(finalDensity)) / (1.775 - sG(initialDensity))) * (sG(finalDensity) / 0.794)
        return DecimalFormat("0.00").format(abv)
    }
}
