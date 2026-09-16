package com.example.listycity3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.listycity3.ui.theme.ListyCity3Theme
// Deleted unused libraries

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cityRepository = CityRepository()
        setContent {
            ListyCity3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        onAddCity = { cityRepository.addCity(it) }, // Part 2, step 2
                        // Lab Participation
                        onUpdateCity = { oldCity, updatedCity ->
                            cityRepository.updateCity(oldCity, updatedCity)
                                       },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}