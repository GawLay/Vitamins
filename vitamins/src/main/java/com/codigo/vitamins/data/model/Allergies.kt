package com.codigo.vitamins.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Allergies (
    @SerializedName("data")
    val allergiesData:List<AllergiesData>
)

@Parcelize
data class AllergiesData(
    val id:String,
    val name:String,
):Parcelable