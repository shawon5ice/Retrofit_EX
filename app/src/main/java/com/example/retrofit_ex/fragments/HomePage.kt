package com.example.retrofit_ex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_ex.R
//import com.example.retrofit_ex.adapter.MainAdapter


class HomePage : Fragment() {


//    val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        view.findViewById<RecyclerView>(R.id.recyclerview).layoutManager = LinearLayoutManager(activity)

//        var list: ArrayList<CustomJob> = ArrayList<CustomJob>()
//        list.add( CustomJob(
//            jobTitle = "SW eng.",
//            recruitingCompanysProfile = "abc ltd.",
//            logo = "https://corporate.bdjobs.com/logos/15131.jpg",
//            deadline = "14th December 2020",
//            isFeatured = false
//        )
//        )
//        list.add( CustomJob(
//            jobTitle = "Backend Developer",
//            recruitingCompanysProfile = "hurry ltd.",
//            logo = "https://corporate.bdjobs.com/logos/15131.jpg",
//            deadline = "14th December 2020",
//            isFeatured = true
//        )
//        )
//        list.add( CustomJob(
//            jobTitle = "BlockChain Developer",
//            recruitingCompanysProfile = "block ltd.",
//            logo = "https://corporate.bdjobs.com/logos/15131.jpg",
//            deadline = "14th December 2020",
//            isFeatured = false
//        )
//        )
//        list.add( CustomJob(
//            jobTitle = "Backend Developer",
//            recruitingCompanysProfile = "hurry ltd.",
//            logo = "https://corporate.bdjobs.com/logos/15131.jpg",
//            deadline = "14th December 2020",
//            isFeatured = false
//        )
//        )
//
//        view.findViewById<RecyclerView>(R.id.recyclerview).adapter = adapter
//        adapter.setJobList(list)
//
//        adapter.setOnClickListener(object : MainAdapter.onItemClickListener{
//            override fun onItemClick(position: Int) {
//                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                transaction?.replace(R.id.mainActivityFragContainer, DetailFragment())
//                transaction?.addToBackStack(null)
//                transaction?.commit()
//            }
//
//        })
        return view
    }

    companion object {

        fun newInstance(): HomePage {
            return HomePage()
        }
    }




}