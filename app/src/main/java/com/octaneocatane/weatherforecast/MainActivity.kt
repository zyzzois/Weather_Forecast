package com.octaneocatane.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.octaneocatane.weatherforecast.databinding.ActivityMainBinding
import com.octaneocatane.weatherforecast.databinding.FragmentMainBinding
import com.octaneocatane.weatherforecast.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction().replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()

    }
}