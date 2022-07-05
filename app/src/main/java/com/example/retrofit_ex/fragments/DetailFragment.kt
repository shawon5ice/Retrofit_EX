package com.example.retrofit_ex.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.retrofit_ex.R
import com.example.retrofit_ex.models.JobResponse


class DetailFragment : Fragment() {
    lateinit var job:JobResponse.Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val bundle = this.arguments
        if (bundle != null) {
            val receivedJob : JobResponse.Data = bundle.getParcelable("jobInfo")!!
            Log.d("rec",receivedJob.jobTitle)
            job = receivedJob
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        view.findViewById<TextView>(R.id.def).text = job.jobTitle
        return view
    }

}