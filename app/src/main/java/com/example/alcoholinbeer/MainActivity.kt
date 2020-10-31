package com.example.alcoholinbeer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            init()
        }
    }
    private fun init() {
        val og = editText.text.toString()
        val fg = editText2.text.toString()
        if (og != "" && fg != "") text.text = ("Алкоголь объемный: ${density(og.toDouble(), fg.toDouble())}%")
        if (og == "" && fg == "") Toast.makeText(this, "Введите плотность", Toast.LENGTH_LONG).show()
        if (og == "" && fg != "") Toast.makeText(this, "Введите начальную плотность", Toast.LENGTH_LONG).show()
        if (og != "" && fg == "") Toast.makeText(this, "Введите конечную плотность", Toast.LENGTH_LONG).show()
    }

    private fun density(og: Double, fg: Double): String {

        fun sG(brix: Double): Double {
            return (brix / (258.6 - ((brix / 258.2) * 227.1))) + 1
        }
        val abv = (76.08 * (sG(og) - sG(fg)) / (1.775 - sG(og))) * (sG(fg) / 0.794)
        return DecimalFormat("0.00").format(abv)
    }
}