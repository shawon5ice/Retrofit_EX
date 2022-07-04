package com.example.retrofit_ex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_ex.repository.MainRepository

class ViewModelFactory constructor(private val repository: MainRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not found")
        }
    }

}
