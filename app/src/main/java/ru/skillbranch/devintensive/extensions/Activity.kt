package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToLong


fun Activity.hideKeyboard(){
    val focus = this.currentFocus
    focus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            it.hideSoftInputFromWindow(focus.windowToken, 0)
        }
    }
}

fun Activity.isKeyboardOpen(): Boolean{
    val rootView = findViewById<View>(android.R.id.content)
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = this.convertDpToPx(50F).roundToLong()

    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}

fun Context.convertDpToPx (dp: Float): Float {
    return TypedValue.applyDimension (
        TypedValue.COMPLEX_UNIT_DIP ,
        dp,
        this. resources . displayMetrics
    )
}

//fun Activity.convertDpToPx(dp: Float): Long {
//    val r = this.resources
//    return TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics).roundToLong()
//}
//
//
//fun Activity.hideKeyboard() {
//    val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
//}
//
//fun Activity.isKeyboardOpen(): Boolean {
//    val r = Rect()
//    val rootView = findViewById<View>(android.R.id.content)
//    rootView.getWindowVisibleDisplayFrame(r)
//    val heightDiff = rootView.height - r.height()
//    val marginOfError = this.convertDpToPx(50F)
//
//    return heightDiff > marginOfError
//}
//
//fun Activity.isKeyboardClosed(): Boolean {
//    return this.isKeyboardOpen().not()
//}



//import android.app.Activity
//import android.content.Context.INPUT_METHOD_SERVICE
//import android.graphics.Rect
//import android.view.inputmethod.InputMethodManager
//
//fun Activity.hideKeyboard() {
//    val inputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
//}
//
//fun Activity.isKeyboardOpen(): Boolean {
//    val r = Rect()
//    window.decorView.getWindowVisibleDisplayFrame(r)
//    return window.decorView.height - (r.bottom - r.top) > window.decorView.height / 4
//}
//
//fun Activity.isKeyboardClosed(): Boolean {
//    return !isKeyboardOpen()
//}