package com.codigo.vitamins.util.ext

import com.codigo.shared.extension.showLog
import com.codigo.vitamins.data.model.HealthConcern
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun String.transformHealthConcernFromJson(): HealthConcern =
    Gson().fromJson(this, HealthConcern::class.java)

inline fun <reified T> String?.toObject(): T? {
    return try {
        GsonBuilder().create().fromJson(this, T::class.java)
    } catch (e: Throwable) {
        "".showLog(e)
        null
    }
}
