package com.example.retrofit_ex.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_ex.R
import com.example.retrofit_ex.databinding.SingleJobItemBinding
import com.example.retrofit_ex.models.JobResponse




class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val binding: SingleJobItemBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
    var jobs = ArrayList<JobResponse.Data>()

    private lateinit var mListener :onItemClickListener


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener
    }
//    fun setJobList(jobs: ArrayList<CustomJob>) {
//        this.jobs = jobs
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleJobItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding,mListener)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val job = jobs[position]
        holder.binding.jobTitle.text = job.jobTitle
        Glide.with(holder.itemView.context).load(job.logo).into(holder.binding.logo)
        holder.binding.deadline.text = job.deadline
        holder.binding.companyName.text = job.recruitingCompanysProfile
        if(job.isFeatured){
            holder.binding.singleJobCardView.setCardBackgroundColor(Color.CYAN)
        }
    }
    override fun getItemCount(): Int {
        return jobs.size
    }
}