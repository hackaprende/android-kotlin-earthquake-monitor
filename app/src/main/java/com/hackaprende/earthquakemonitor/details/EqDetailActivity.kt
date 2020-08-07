package com.hackaprende.earthquakemonitor.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.R
import com.hackaprende.earthquakemonitor.databinding.ActivityEqDetailBinding

class EqDetailActivity : AppCompatActivity() {
    companion object {
        const val EQ_KEY = "earthquake"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEqDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val earthquake = intent?.extras?.getParcelable<Earthquake>(EQ_KEY)!!
        binding.magnitudeText.text = getString(R.string.magnitude_format, earthquake.magnitude)
        binding.longitudeText.text = earthquake.longitude.toString()
        binding.latitudeText.text = earthquake.latitude.toString()
        binding.placeText.text = earthquake.place
        binding.timeText.text = earthquake.time.toString()
    }
}