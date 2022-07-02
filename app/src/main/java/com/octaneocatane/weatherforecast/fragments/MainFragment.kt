package com.octaneocatane.weatherforecast.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.octaneocatane.weatherforecast.adapters.ViewPager2Adapter
import com.octaneocatane.weatherforecast.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var pLauncher: ActivityResultLauncher<String>

    private val fList = listOf(HoursFragment.newInstance(), DaysFragment.newInstance())
    private val tabList = listOf("Hours", "Days")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*когда все view созданы мы делаем проверку на то есть ли у нас разрешение*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
    }

    private fun init() = with(binding) {
        val adapter = ViewPager2Adapter(activity as AppCompatActivity, fList)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2){
            //tab - наш элемент tab на который мы нажали
            tab, pos -> tab.text = tabList[pos]
        }.attach()
    }


    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    /*у нас выходит диалог если у нас еще нет разрешения*/
    private fun checkPermission(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            //спрашиваем разрешение(выводим диалог)
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}