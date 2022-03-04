package com.example.utils

import android.util.Log
import android.view.View
import android.view.ViewTreeObserver


fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.logRenderTime() {

    var startLogTime = System.currentTimeMillis()

    viewTreeObserver.addOnDrawListener {
        startLogTime = System.currentTimeMillis()
    }

    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

        override fun onGlobalLayout() {
            Log.d(
                "Viettel",
                "Fetch time - ${getStringId()}: ${System.currentTimeMillis() - startLogTime}ms"
            )
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}

fun View.getStringId(): String {
    return if (id == View.NO_ID) "no-id" else resources.getResourceName(id)
}
