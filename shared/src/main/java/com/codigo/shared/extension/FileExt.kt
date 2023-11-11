package com.codigo.shared.extension

import android.content.res.AssetManager

fun AssetManager.readAssetFile(fileName: String) =
    open(fileName).bufferedReader().use {
        it.readText()
    }