package com.hackaprende.earthquakemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackaprende.earthquakemonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.eqRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = EqAdapter()
        recyclerView.adapter = adapter

        val eqList = mutableListOf<Earthquake>()
        eqList.add(Earthquake("1", "Earthquake 1", 5.5, 1234456778L, "red", 24.1029, -110.17236))
        eqList.add(Earthquake("2", "Earthquake 2", 8.2, 1234456778L, "red", 24.1029, -110.17236))
        eqList.add(Earthquake("3", "Earthquake 3", 3.0, 1234456778L, "red", 24.1029, -110.17236))
        eqList.add(Earthquake("4", "Earthquake 4", 4.3, 1234456778L, "red", 24.1029, -110.17236))
        eqList.add(Earthquake("5", "Earthquake 5", 4.8, 1234456778L, "red", 24.1029, -110.17236))
        eqList.add(Earthquake("6", "Earthquake 6", 7.1, 1234456778L, "red", 24.1029, -110.17236))
        adapter.submitList(eqList)

        if (adapter.itemCount == 0) {
            recyclerView.visibility = View.GONE
            binding.eqEmptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            binding.eqEmptyView.visibility = View.GONE
        }
    }
}