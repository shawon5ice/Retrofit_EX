package com.example.retrofit_ex.api

import com.example.retrofit_ex.models.JobResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("interviewJson.json")
    fun getJobResponse(): Call<JobResponse>

    companion object{
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if(retrofitService==null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://corporate3.bdjobs.com/interviewtest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}