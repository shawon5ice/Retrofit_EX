package com.example.retrofit_ex.models


import android.os.Parcel
import android.os.Parcelable
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
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readByte() != 0.toByte(),
            TODO("jobDetails"),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

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

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(deadline)
            parcel.writeByte(if (isFeatured) 1 else 0)
            parcel.writeString(jobTitle)
            parcel.writeString(logo)
            parcel.writeInt(maxExperience)
            parcel.writeString(maxSalary)
            parcel.writeInt(minExperience)
            parcel.writeString(minSalary)
            parcel.writeString(recruitingCompanysProfile)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }
    }
}