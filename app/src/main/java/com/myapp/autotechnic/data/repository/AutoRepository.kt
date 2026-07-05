package com.myapp.autotechnic.data.repository

import com.myapp.autotechnic.data.remote.AutoApiService
import com.myapp.autotechnic.domain.model.*

interface AutoRepository {
    suspend fun getBrands(): List<Brand>
    suspend fun getModels(brandId: String): List<CarModel>
    suspend fun getGenerations(brandId: String, modelId: String): List<Generation>
    suspend fun getSpecs(brandName: String, modelName: String, generationName: String): TechnicalSpecs?
}

class RemoteAutoRepository(private val apiService: AutoApiService) : AutoRepository {
    override suspend fun getBrands(): List<Brand> {
        return try {
            val response = apiService.getMakes()
            response.results.map { Brand(it.id.toString(), it.name) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getModels(brandId: String): List<CarModel> {
        return try {
            val response = apiService.getModels(brandId.toInt())
            response.results.map { CarModel(it.id.toString(), brandId, it.name) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getGenerations(brandId: String, modelId: String): List<Generation> {
        // We simulate generations with different performance levels
        return listOf(
            Generation("${modelId}_std", modelId, "Standard Base Edition", "2020+"),
            Generation("${modelId}_perf", modelId, "High Performance Trim", "2021+"),
            Generation("${modelId}_eco", modelId, "Efficiency/Hybrid Package", "2022+")
        )
    }

    override suspend fun getSpecs(brandName: String, modelName: String, generationName: String): TechnicalSpecs? {
        // 1. Check our library for real data first
        val librarySpecs = SpecsLibrary.getSpecs(brandName, modelName)
        if (librarySpecs != null && generationName.contains("Standard", ignoreCase = true)) {
            return librarySpecs
        }

        // 2. Fallback to context-aware generator if not in library or special generation
        val isPerformance = generationName.contains("Performance", ignoreCase = true)
        val isEco = generationName.contains("Efficiency", ignoreCase = true)

        val baseHp = when {
            brandName.contains("FERRARI", true) || brandName.contains("LAMBORGHINI", true) -> 600
            brandName.contains("PORSCHE", true) || brandName.contains("BMW", true) && modelName.contains("M") -> 450
            brandName.contains("BMW", true) || brandName.contains("MERCEDES", true) || brandName.contains("AUDI", true) -> 200
            else -> 130
        }

        val hp = if (isPerformance) (baseHp * 1.5).toInt() else if (isEco) (baseHp * 0.8).toInt() else baseHp
        val torque = (hp * 1.4).toInt()
        val topSpeed = if (hp > 400) 290 else if (hp > 200) 240 else 190

        return TechnicalSpecs(
            generationId = generationName,
            engine = if (isEco) "1.5L Inline-4 Hybrid" else if (hp > 400) "4.0L V8 Twin-Turbo" else "2.0L Inline-4 Turbo",
            power = "$hp hp @ 6000 rpm",
            torque = "$torque Nm @ 2500 rpm",
            topSpeed = "$topSpeed km/h",
            acceleration0100 = if (hp > 400) "3.8 sec" else if (hp > 200) "6.2 sec" else "9.5 sec",
            fuelConsumption = if (isEco) "4.2 L/100km" else if (hp > 400) "12.5 L/100km" else "7.5 L/100km",
            transmission = if (hp > 400) "7-Speed Dual-Clutch" else "8-Speed Automatic",
            driveType = if (hp > 400 || brandName.contains("TESLA", true)) "AWD" else "RWD",
            length = "4750 mm",
            width = "1850 mm",
            height = "1440 mm",
            weight = "${1400 + (hp / 2)} kg",
            bootSpace = "450 L"
        )
    }
}
