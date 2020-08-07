package com.hackaprende.earthquakemonitor.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackaprende.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this,
            MainViewModelFactory(application)).get(MainViewModel::class.java)

        val recyclerView = binding.eqRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = EqAdapter(this)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            Toast.makeText(this, "Earthquake magnitude: ${it.magnitude}", Toast.LENGTH_SHORT).show()
        }

        viewModel.eqListLiveData.observe(this, Observer {
            adapter.submitList(it)
            if (it.size == 0) {
                recyclerView.visibility = View.GONE
                binding.eqEmptyView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                binding.eqEmptyView.visibility = View.GONE
            }
        })
    }
}