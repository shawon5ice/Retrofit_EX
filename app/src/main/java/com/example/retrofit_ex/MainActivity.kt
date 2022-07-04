package com.example.retrofit_ex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_ex.api.RetrofitService
import com.example.retrofit_ex.databinding.ActivityMainBinding
import com.example.retrofit_ex.fragments.HomePage
import com.example.retrofit_ex.repository.MainRepository
import com.example.retrofit_ex.viewmodel.MainViewModel
import com.example.retrofit_ex.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.putString("Hello", "SaveIt")
        setContentView(R.layout.activity_main)

        val homeFragment = HomePage()

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragContainer, homeFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }


        viewModel = ViewModelProvider(this, ViewModelFactory(MainRepository(retrofitService)))
            .get(MainViewModel::class.java)



    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            Log.i("MainActivity", "popping backstack")
            supportFragmentManager.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            super.onBackPressed()
        }
    }


}