package com.codigo.vitamins.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Diets(
    @SerializedName("data")
    val list: List<DietsData>
)

@Parcelize
data class DietsData(
    val id: Int,
    val name: String,
    @SerializedName("tool_tip")
    val toolTip: String,
    var isSelected: Boolean,
) : Parcelable