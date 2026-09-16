package com.example.listycity3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
// Part 2, step 1
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
// Part 3, step 1
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize
// Lab Participation
import androidx.compose.foundation.clickable // From 'Hints'
import androidx.compose.ui.graphics.Color

@Composable
fun CityListScreen(
    cities: List<City>,
    onAddCity: (City)-> Unit, // Part 2, step 3
    onUpdateCity: (City, City)-> Unit, // Lab Participation, to be able to update the city
    modifier: Modifier = Modifier
) {
    // Part 2, step 4
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    var showAddCityFields by remember { mutableStateOf(false) } // Part 3, step 2
    var showEditCityFields by remember { mutableStateOf(false) } // Lab Participation - For the edit button
    var selectCityName by remember { mutableStateOf<String?>(null)} // Lab Participation - For selectability - I took this from my Lab 2 work
    var selectProvinceName by remember { mutableStateOf<String?>(null)} // Lab Participation - For selectability - I took this from my Lab 2 work


    // Part 2, step 5
    Column(modifier = modifier.fillMaxSize()) { // Part 3, step 3
        // Lab Participation, for my own visuals
        Row(modifier = Modifier.padding(all = 16.dp)){
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "ListyCity v2",
                fontSize = 40.sp
            )
        }

        // Part 3, step 4
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields
                    showEditCityFields = false // Lab Participation - Just added an Edit floating action button
                    // Lab Participate - Setting everything to null
                    selectCityName = null
                    selectProvinceName = null
                }
            ) {
                Text("+")
            }

            // Lab Participation - Getting an Edit drop down
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showEditCityFields = !showEditCityFields
                    showAddCityFields = false
                    selectCityName = null
                    selectProvinceName = null
                }
            ) {
                Text("Edit")
            }
        }



        // Part 3, step 5
        if (showAddCityFields or showEditCityFields) { // Updated with Lab Participation to have the showEditCityFields
            if (showEditCityFields) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Lab Participation - Clarification on how to edit
                    Text(
                        text = "To edit a city, please select a city/province and then update the name & province through the textboxes.",
                        fontSize = 12.sp
                    )
                }
            }
            // Part 2, step 6
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Part 2, step 7
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )
                // Part 2, step 8
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )
                // Part 2, step 9
                Spacer(modifier = Modifier.width(8.dp))
                if (showAddCityFields){ // Lab Participation, just implementing the showAddCityFields portion
                    Button(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = {
                            if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                                onAddCity(
                                    City(
                                        name = newCityName,
                                        province = newProvinceName
                                    )
                                )
                                newCityName = ""
                                newProvinceName = ""
                                showAddCityFields = false // Part 3, step 6
                            }
                        }
                    ) {
                        Text("Add City")
                    }
                }

                // Updated with Lab Participation to show to showEditCityFields portion
                if (showEditCityFields){
                    Button(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = {
                            if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                                onUpdateCity(
                                    City(
                                        name = selectCityName.toString(),
                                        province = selectProvinceName.toString()
                                    ),
                                    City(
                                        name = newCityName,
                                        province = newProvinceName
                                    )
                                )
                                newCityName = ""
                                newProvinceName = ""
                                showEditCityFields = false
                            }
                        }
                    ) {
                        Text("Edit City")
                    }

                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) { // Part 3, step 7
                itemsIndexed(cities) { index, city ->
                    CityRow(
                        // Lab Participation - For selectability - I took this from my Lab 2 work
                        city = city,
                        selected = ((city.name == selectCityName) and (city.province == selectProvinceName)),
                        editMode = showEditCityFields, // Lab Participation - Only selectable when in edit mode
                        onClick = {
                            if (selectCityName == city.name && selectProvinceName == city.province) {
                                selectCityName = null
                                selectProvinceName = null
                            }
                            else {
                                selectCityName = city.name
                                selectProvinceName = city.province
                            }
                        }
                    )
                    if (index < cities.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    city: City,
    // Lab Participation - For selectability - I took this from my Lab 2 work
    selected: Boolean = false,
    onClick: () -> Unit = {},
    editMode: Boolean = false, // To only select when on Edit city mode
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            // Lab Participation - For selectability - I took this from my Lab 2 work
            modifier = Modifier
                .weight(1f)
                .background(
                    if (selected && editMode) {
                        Color.Gray
                    }
                    else {
                        Color.White
                    }
                )
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable { onClick() }
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            // Lab Participation - For selectability - I took this from my Lab 2 work
            modifier = Modifier
                .weight(1f)
                .background(
                    if (selected && editMode) {
                        Color.Gray
                    }
                    else {
                        Color.White
                    }
                )
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable { onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
// Part 2, Update Step
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
            onAddCity = {},
            onUpdateCity = {_, _ -> }  // I was having an error with unused parameters: https://medium.com/@dmstocking/kotlin-considered-harmful-unused-parameters-211846add4c0 - Accessed 09-16-2026
        )
    }
}