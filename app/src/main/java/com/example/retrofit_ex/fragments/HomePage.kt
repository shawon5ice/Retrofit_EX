package com.example.retrofit_ex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_ex.Communicator
import com.example.retrofit_ex.MainActivity
import com.example.retrofit_ex.R
import com.example.retrofit_ex.adapter.MainAdapter
import com.example.retrofit_ex.api.RetrofitService.Companion.retrofitService
import com.example.retrofit_ex.databinding.FragmentDetailBinding
import com.example.retrofit_ex.databinding.FragmentHomePageBinding
import com.example.retrofit_ex.models.JobResponse
import com.example.retrofit_ex.repository.MainRepository
import com.example.retrofit_ex.viewmodel.MainViewModel
import com.example.retrofit_ex.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class HomePage : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var communicator: Communicator

    val adapter = MainAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomePageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_page, container, false
        )
        val view: View = binding.root

        communicator = activity as Communicator

        binding.recyclerview.layoutManager = LinearLayoutManager(activity)

        binding.recyclerview.adapter = adapter

        var data : ArrayList<JobResponse.Data> = ArrayList()
        adapter.jobs = data

        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService!!)))
            .get(MainViewModel::class.java)

        viewModel.jobResponse.observe(viewLifecycleOwner) {
            if(it!=null){
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
            }
            Log.d("res", "onCreate: ${it.data}")
            data.addAll(it.data)
            adapter.notifyDataSetChanged()

        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
            Snackbar.make(view,"Something went wrong",Snackbar.LENGTH_SHORT).show()
        }

        viewModel.getJobs()

        adapter.setOnClickListener(object : MainAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                communicator.passJobData(data[position])
            }

        })
        return view
    }

}