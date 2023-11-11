package com.codigo.shared.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Any.showLog(message: Any) = Log.e(this::class.java.name, message.toString())

fun Context.showToast(message:String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
