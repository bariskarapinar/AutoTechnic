package com.myapp.autotechnic.data.model

import com.google.gson.annotations.SerializedName

data class NhtsaResponse<T>(
    @SerializedName("Count") val count: Int,
    @SerializedName("Message") val message: String,
    @SerializedName("Results") val results: List<T>
)

data class ApiMake(
    @SerializedName("MakeId") val id: Int,
    @SerializedName("MakeName") val name: String
)

data class ApiModel(
    @SerializedName("Model_ID") val id: Int,
    @SerializedName("Model_Name") val name: String,
    @SerializedName("Make_ID") val makeId: Int,
    @SerializedName("Make_Name") val makeName: String
)

data class ApiVinSpecs(
    @SerializedName("DisplacementCC") val displacementCc: String?,
    @SerializedName("EngineHP") val horsepower: String?,
    @SerializedName("EngineConfiguration") val engineConfig: String?,
    @SerializedName("EngineCylinders") val cylinders: String?,
    @SerializedName("FuelTypePrimary") val fuelType: String?,
    @SerializedName("DriveType") val driveType: String?,
    @SerializedName("TransmissionStyle") val transmission: String?,
    @SerializedName("BodyClass") val bodyClass: String?,
    @SerializedName("Doors") val doors: String?
)
