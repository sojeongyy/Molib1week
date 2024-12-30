package com.example.opsidian.data.models

import java.io.Serializable
// 자동차
data class CarModel(
    val name: String,
    val imageName: String,
    val brand: String,
    val launchYear: String,
    val options: String,
    val horsepower: String,
    val modelYear: Int,
    val fuelEconomy: String,
    val price: String
): Serializable