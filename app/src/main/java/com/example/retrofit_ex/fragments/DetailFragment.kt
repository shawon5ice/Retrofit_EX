package com.example.retrofit_ex.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.retrofit_ex.R
import com.example.retrofit_ex.databinding.FragmentDetailBinding
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
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        val view: View = binding.root

        binding.jobTitle.text = job.jobTitle
        binding.companyName.text = job.jobTitle
        Glide.with(context!!).load(job.logo).into(binding.logo)
        var salary = "Salary:"
        if(job.minSalary.isEmpty() && job.maxSalary.isEmpty()){
            binding.salary.text = "Negotiable"
        }else{
            if(job.minSalary.isEmpty()){
                salary+=job.maxSalary
            }else if(job.maxSalary.isEmpty()){
                salary+=job.minSalary
            }else{
                salary+=job.minSalary +" - " + job.maxSalary
            }
            binding.salary.text = salary
        }

        var exp = ""

        if(job.minExperience==0 && job.maxExperience==0){
            binding.experience.text = "NA"
        }else{
            if(job.minExperience==0){
                exp+=job.maxExperience.toString() + " Years"
            }else if(job.maxExperience==0){
                exp+=job.minExperience.toString() + " Years"
            }else{
                exp+=job.minExperience.toString() +" - " + job.maxExperience + " Years"
            }
            binding.experience.text = exp
        }

        binding.jobDescription.text = Html.fromHtml(job.jobDetails.applyInstruction,Html.FROM_HTML_MODE_LEGACY)


        return view
    }

}