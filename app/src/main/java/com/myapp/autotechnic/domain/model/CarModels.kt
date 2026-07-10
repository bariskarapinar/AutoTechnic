package com.myapp.autotechnic.domain.model

data class Brand(
    val id: String,
    val name: String,
    val logoUrl: String? = null
)

data class CarModel(
    val id: String,
    val brandId: String,
    val name: String
)

data class Generation(
    val id: String,
    val modelId: String,
    val name: String, // e.g., "2019 - 2023 (G20)"
    val years: String
)

data class TechnicalSpecs(
    val generationId: String,
    val engine: String,
    val power: String,
    val torque: String,
    val topSpeed: String,
    val acceleration0100: String,
    val fuelConsumption: String,
    val transmission: String,
    val driveType: String,
    val length: String,
    val width: String,
    val height: String,
    val weight: String,
    val bootSpace: String
)
