package com.example.alcoholinbeer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alcoholinbeer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java).also {
            binding.viewmodel = it
        }

        binding.buttonResult.setOnClickListener {
            viewModel.onResultButtonClick()

            if (binding.editTextInitialDensity.text.toString()
                    .isEmpty() || binding.editTextFinalDensity.text.toString()
                    .isEmpty()
            ) {
                viewModel.message.observe(this, Observer {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}