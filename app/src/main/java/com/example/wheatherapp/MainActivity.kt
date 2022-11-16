package com.example.wheatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.wheatherapp.databinding.ActivityMainBinding
import com.example.wheatherapp.fragments.MainFragment
import org.json.JSONArray
import org.json.JSONObject

/*
Отображение прогноза погоды за текущий день
Отображение прогноза погоды за неделю
Возможность увидеть прогноз погоды в текущем городе
Возможность выбрать любой другой город, и узнать прогноз погоды в нем
 */

const val API_KEY = "5b7eccfd389946bb809132017221611"


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

    private fun getResult(name: String) {
        val url = "http://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
            },
            {
                Log.d("MyLog", "Error: $it")
            }
        )
        queue.add(stringRequest)
    }


}


