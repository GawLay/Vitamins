package com.codigo.vitamins.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataToPrint (
    @SerializedName("diets")
    var dietsData: List<DietsData>? = listOf(),
    @SerializedName("health_concerns")
    var healthConcernData: List<HealthConcernData>? = listOf(),
    @SerializedName("allergies")
    var allergiesSelectedData:List<AllergiesData>? = listOf(),
    @SerializedName("is_daily_exposure")
    var isDailyExposure: Boolean? = null,
    @SerializedName("is_somke")
    var isSmoke: Boolean? = null,
    @SerializedName("alchol")
    var alcohol: String? = null
):Parcelable