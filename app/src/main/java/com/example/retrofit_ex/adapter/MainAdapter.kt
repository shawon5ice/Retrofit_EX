package com.example.retrofit_ex.adapter

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_ex.R
import com.example.retrofit_ex.databinding.SingleJobItemBinding
import com.example.retrofit_ex.models.JobResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleJobItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding,mListener)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val job = jobs[position]
        holder.binding.jobTitle.text = job.jobTitle
        Glide.with(holder.itemView.context).load(job.logo).into(holder.binding.logo)

        val datePart = job.jobDetails.lastDate.split(" ").toTypedArray()
        holder.binding.deadline.text = datePart[0]+" " + datePart[1]+", " + datePart[2]
        holder.setIsRecyclable(false)
        holder.binding.companyName.text = job.recruitingCompanysProfile
        if(job.isFeatured){
//            holder.binding.singleJobCardView.setBackgroundResource(R.drawable.shape)
            holder.binding.singleJobCardView.setCardBackgroundColor(Color.argb(255,255,150,0))
//            holder.binding.singleJobCardView.elevation = 10.0.toFloat()
        }
    }
    override fun getItemCount(): Int {
        return jobs.size
    }
}