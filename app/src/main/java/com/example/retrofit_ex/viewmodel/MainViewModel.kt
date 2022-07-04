package com.example.retrofit_ex.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_ex.models.JobResponse
import com.example.retrofit_ex.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val jobResponse = MutableLiveData<List<JobResponse.Data>>()
    val errorMessage = MutableLiveData<String>()

    fun getJobs() {
        val response = repository.getJobResponse()
        response.enqueue(object : Callback<List<JobResponse.Data>> {
            override fun onResponse(call: Call<List<JobResponse.Data>>, response: Response<List<JobResponse.Data>>) {
                //jobResponse.postValue(response.body()?.data)
                
                jobResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<List<JobResponse.Data>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<JobResponse.Data>>) {

}
