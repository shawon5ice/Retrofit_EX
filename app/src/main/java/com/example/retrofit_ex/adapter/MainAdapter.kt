package com.example.retrofit_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_ex.R
import com.example.retrofit_ex.databinding.SingleJobItemBinding
import com.example.retrofit_ex.models.JobResponse


class MainViewHolder(val binding: SingleJobItemBinding) : RecyclerView.ViewHolder(binding.root) {
}

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var jobs = mutableListOf<JobResponse.Data>()

    fun setJobList(jobs: List<JobResponse.Data>) {
        this.jobs = jobs.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleJobItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val job = jobs[position]
        holder.binding.jobTitle.text = job.jobTitle
        Glide.with(holder.itemView.context).load(job.logo).into(holder.binding.logo)
        holder.binding.deadline.text = job.deadline
        holder.binding.companyName.text = job.recruitingCompanysProfile
//        if(job.isFeatured){
//            holder.binding.singleJobCardView.background
//        }
    }
    override fun getItemCount(): Int {
        return jobs.size
    }
}