package com.example.wheatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.example.wheatherapp.databinding.ActivityMainBinding


/*
Отображение прогноза погоды за текущий день
Отображение прогноза погоды за неделю
Возможность увидеть прогноз погоды в текущем городе
Возможность выбрать любой другой город, и узнать прогноз погоды в нем
 */



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()

    }


}


