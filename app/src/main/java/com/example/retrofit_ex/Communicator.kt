package com.example.retrofit_ex

import com.example.retrofit_ex.models.JobResponse

interface Communicator {
    fun passJobData(job: JobResponse.Data)
}