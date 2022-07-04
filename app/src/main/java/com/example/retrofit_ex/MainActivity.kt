package com.example.retrofit_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_ex.adapter.MainAdapter
import com.example.retrofit_ex.api.RetrofitService
import com.example.retrofit_ex.databinding.ActivityMainBinding
import com.example.retrofit_ex.repository.MainRepository
import com.example.retrofit_ex.viewmodel.MainViewModel
import com.example.retrofit_ex.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.putString("Hello","SaveIt")
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter



        viewModel.jobResponse.observe(this) {
            Log.d("res", "onCreate: $it")
            adapter.setJobList(it.data)
        }
        viewModel.errorMessage.observe(this) {
        }
        viewModel.getJobs()
    }
}