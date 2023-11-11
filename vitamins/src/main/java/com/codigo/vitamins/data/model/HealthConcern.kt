package com.codigo.vitamins.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HealthConcern(
    @SerializedName("data")
    val list: List<HealthConcernData>
)

@Parcelize
data class HealthConcernData(
    val id: Int,
    val name: String,
    var isSelected: Boolean,
    var position:Int = 0
) : Parcelable