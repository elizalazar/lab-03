package com.example.listycity3
import androidx.compose.runtime.mutableStateListOf // Part 1, step 1

class CityRepository {
    // Part 1, step 2
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    // Part 1, step 3
    fun addCity(city: City) {
        _cities.add(city)
    }
}