package com.hackaprende.earthquakemonitor.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.earthquakemonitor.R
import com.hackaprende.earthquakemonitor.databinding.ActivityEqDetailBinding

class EqDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEqDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}