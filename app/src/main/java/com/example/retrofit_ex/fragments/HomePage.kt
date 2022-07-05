package com.example.retrofit_ex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_ex.MainActivity
import com.example.retrofit_ex.R
import com.example.retrofit_ex.adapter.MainAdapter
import com.example.retrofit_ex.api.RetrofitService.Companion.retrofitService
import com.example.retrofit_ex.models.JobResponse
import com.example.retrofit_ex.repository.MainRepository
import com.example.retrofit_ex.viewmodel.MainViewModel
import com.example.retrofit_ex.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class HomePage : Fragment() {

    lateinit var viewModel: MainViewModel

    val adapter = MainAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        view.findViewById<RecyclerView>(R.id.recyclerview).layoutManager = LinearLayoutManager(activity)

        view.findViewById<RecyclerView>(R.id.recyclerview).adapter = adapter

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
                val fragment = DetailFragment(); //Your Fragment
                val bundle = Bundle()

                bundle.putParcelable("jobInfo", data[position])  // Key, value
                fragment.setArguments(bundle);
                replaceFragment(MainActivity.fragmentManger,fragment)

            }

        })
        return view
    }

    fun replaceFragment(manager: FragmentManager, fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val fragmentPopped: Boolean = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) { //fragment not in back stack, create it.
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.replace(com.example.retrofit_ex.R.id.mainActivityFragContainer, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }


}