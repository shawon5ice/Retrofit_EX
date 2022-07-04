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

    val jobResponse = MutableLiveData<JobResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getJobs() {
        val response = repository.getJobResponse()
        response.enqueue(object : Callback<JobResponse> {
            override fun onResponse(call: Call<JobResponse>,
                                    response: Response<JobResponse>) {

                jobResponse.postValue(response.body())
            }
            override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<JobResponse.Data>>) {

}
