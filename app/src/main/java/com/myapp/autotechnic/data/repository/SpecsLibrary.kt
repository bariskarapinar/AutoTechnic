package com.myapp.autotechnic.data.repository

import com.myapp.autotechnic.domain.model.TechnicalSpecs

object SpecsLibrary {
    private val data = mapOf(
        "BMW_3 SERIES" to TechnicalSpecs(
            generationId = "3_series",
            engine = "2.0L Inline-4 TwinPower Turbo",
            power = "255 hp @ 5000-6500 rpm",
            torque = "400 Nm @ 1550-4400 rpm",
            topSpeed = "250 km/h",
            acceleration0100 = "5.6 sec",
            fuelConsumption = "6.6 L/100km",
            transmission = "8-Speed Steptronic",
            driveType = "RWD / AWD",
            length = "4709 mm",
            width = "1827 mm",
            height = "1435 mm",
            weight = "1540 kg",
            bootSpace = "480 L"
        ),
        "BMW_M3" to TechnicalSpecs(
            generationId = "m3",
            engine = "3.0L Inline-6 M TwinPower Turbo",
            power = "473 hp @ 6250 rpm",
            torque = "550 Nm @ 2750-5500 rpm",
            topSpeed = "250 km/h (290 km/h with M Driver's Package)",
            acceleration0100 = "4.2 sec",
            fuelConsumption = "10.8 L/100km",
            transmission = "6-Speed Manual / 8-Speed M Steptronic",
            driveType = "RWD",
            length = "4794 mm",
            width = "1903 mm",
            height = "1433 mm",
            weight = "1780 kg",
            bootSpace = "480 L"
        ),
        "TESLA_MODEL 3" to TechnicalSpecs(
            generationId = "model_3",
            engine = "Dual Motor All-Wheel Drive",
            power = "450 hp (Long Range)",
            torque = "639 Nm",
            topSpeed = "233 km/h",
            acceleration0100 = "4.4 sec",
            fuelConsumption = "Electric (16.0 kWh/100km)",
            transmission = "Single-Speed",
            driveType = "AWD",
            length = "4694 mm",
            width = "1849 mm",
            height = "1443 mm",
            weight = "1847 kg",
            bootSpace = "649 L (Total)"
        ),
        "MERCEDES-BENZ_C-CLASS" to TechnicalSpecs(
            generationId = "c_class",
            engine = "2.0L Inline-4 with EQ Boost",
            power = "255 hp @ 5800 rpm",
            torque = "400 Nm @ 2000-4000 rpm",
            topSpeed = "210 km/h",
            acceleration0100 = "6.0 sec",
            fuelConsumption = "7.0 L/100km",
            transmission = "9G-TRONIC Automatic",
            driveType = "RWD / 4MATIC",
            length = "4751 mm",
            width = "1820 mm",
            height = "1438 mm",
            weight = "1650 kg",
            bootSpace = "455 L"
        ),
        "TOYOTA_COROLLA" to TechnicalSpecs(
            generationId = "corolla",
            engine = "1.8L 4-Cylinder Hybrid",
            power = "121 hp @ 5200 rpm",
            torque = "142 Nm @ 3600 rpm",
            topSpeed = "180 km/h",
            acceleration0100 = "10.5 sec",
            fuelConsumption = "4.3 L/100km",
            transmission = "Electronically controlled CVT (eCVT)",
            driveType = "FWD",
            length = "4630 mm",
            width = "1780 mm",
            height = "1435 mm",
            weight = "1385 kg",
            bootSpace = "471 L"
        ),
        "AUDI_A4" to TechnicalSpecs(
            generationId = "a4",
            engine = "2.0 TFSI 4-cylinder",
            power = "201 hp",
            torque = "320 Nm",
            topSpeed = "210 km/h",
            acceleration0100 = "6.7 sec",
            fuelConsumption = "6.5 L/100km",
            transmission = "7-speed S tronic",
            driveType = "Quattro AWD",
            length = "4762 mm",
            width = "1847 mm",
            height = "1428 mm",
            weight = "1580 kg",
            bootSpace = "460 L"
        ),
        "PORSCHE_911" to TechnicalSpecs(
            generationId = "911",
            engine = "3.0L 6-cylinder Boxer Twin-Turbo",
            power = "379 hp @ 6500 rpm",
            torque = "450 Nm @ 1950-5000 rpm",
            topSpeed = "293 km/h",
            acceleration0100 = "4.2 sec",
            fuelConsumption = "9.4 L/100km",
            transmission = "8-speed Porsche Doppelkupplung (PDK)",
            driveType = "RWD",
            length = "4519 mm",
            width = "1852 mm",
            height = "1298 mm",
            weight = "1505 kg",
            bootSpace = "132 L"
        )
    )

    fun getSpecs(brandName: String, modelName: String): TechnicalSpecs? {
        val key = "${brandName.uppercase()}_${modelName.uppercase()}"
        return data[key] ?: data.entries.find { it.key.contains(modelName.uppercase()) }?.value
    }
}
