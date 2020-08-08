package com.hackaprende.earthquakemonitor.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.R
import com.hackaprende.earthquakemonitor.api.ApiResponseStatus
import com.hackaprende.earthquakemonitor.databinding.ActivityMainBinding
import com.hackaprende.earthquakemonitor.details.EqDetailActivity

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
            openDetailActivity(it)
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

        viewModel.statusLiveData.observe(this, Observer {
            if (it == ApiResponseStatus.LOADING) {
                binding.loadingWheel.visibility = View.VISIBLE
            } else {
                binding.loadingWheel.visibility = View.GONE
            }

            if (it == ApiResponseStatus.NO_INTERNET_CONNECTION) {
                Toast.makeText(this, R.string.no_internet_connection,
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openDetailActivity(earthquake: Earthquake) {
        val intent = Intent(this, EqDetailActivity::class.java)
        intent.putExtra(EqDetailActivity.EQ_KEY, earthquake)
        startActivity(intent)
    }
}