package com.example.retrofit_ex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_ex.adapter.MainAdapter
import com.example.retrofit_ex.api.RetrofitService
import com.example.retrofit_ex.databinding.ActivityMainBinding
import com.example.retrofit_ex.fragments.DetailFragment
import com.example.retrofit_ex.fragments.HomePage
import com.example.retrofit_ex.models.JobResponse
import com.example.retrofit_ex.repository.MainRepository
import com.example.retrofit_ex.viewmodel.MainViewModel
import com.example.retrofit_ex.viewmodel.ViewModelFactory
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    companion object {
        lateinit var fragmentManger: FragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManger = supportFragmentManager

        val homeFragment = HomePage()

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragContainer, homeFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }




    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount >1) {
            Log.i("MainActivity", "popping backstack")
            supportFragmentManager.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            supportFragmentManager.popBackStack()
            super.onBackPressed()
        }
    }

    override fun passJobData(job: JobResponse.Data) {
        val bundle = Bundle()
        val fragment = DetailFragment();
        bundle.putParcelable("jobInfo", job)
        fragment.arguments = bundle;
        replaceFragment(fragmentManger,fragment)
    }

    private fun replaceFragment(manager: FragmentManager, fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val fragmentPopped: Boolean = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft: FragmentTransaction = manager.beginTransaction()
            ft.replace(R.id.mainActivityFragContainer, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

}