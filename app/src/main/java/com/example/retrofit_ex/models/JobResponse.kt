package com.example.retrofit_ex.models


import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("common")
    val common: Common,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statuscode")
    val statuscode: String
) {
    data class Common(
        @SerializedName("total_records_found")
        val totalRecordsFound: Int,
        @SerializedName("totalpages")
        val totalpages: Int
    )

    data class Data(
        @SerializedName("deadline")
        val deadline: String,
        @SerializedName("IsFeatured")
        val isFeatured: Boolean,
        @SerializedName("jobDetails")
        val jobDetails: JobDetails,
        @SerializedName("jobTitle")
        val jobTitle: String,
        @SerializedName("logo")
        val logo: String,
        @SerializedName("maxExperience")
        val maxExperience: Int,
        @SerializedName("maxSalary")
        val maxSalary: String,
        @SerializedName("minExperience")
        val minExperience: Int,
        @SerializedName("minSalary")
        val minSalary: String,
        @SerializedName("recruitingCompany'sProfile")
        val recruitingCompanysProfile: String
    ) {
        data class JobDetails(
            @SerializedName("ApplyInstruction")
            val applyInstruction: String,
            @SerializedName("CompanyName")
            val companyName: String,
            @SerializedName("LastDate")
            val lastDate: String,
            @SerializedName("Title")
            val title: String
        )
    }
}