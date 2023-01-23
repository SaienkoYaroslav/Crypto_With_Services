package com.example.cryptowithservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptowithservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newServiceIntent(this, 50))
        }

    }


}