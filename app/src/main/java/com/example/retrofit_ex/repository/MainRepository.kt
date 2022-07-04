package com.example.retrofit_ex.repository

import com.example.retrofit_ex.api.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getJobResponse() = retrofitService.getJobResponse()
}