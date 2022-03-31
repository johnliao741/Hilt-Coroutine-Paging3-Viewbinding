package tw.personal.jehuty.fsdation.util

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.NumberFormat
import java.util.*

fun Int.toPriceString():String =
    "$${NumberFormat.getNumberInstance(Locale.US).format(this)}"
fun Int.toStrikePriceString():SpannableString{
    val display = this.toString()
    val spannableString = SpannableString(display)
    //添加中劃線
    spannableString.setSpan(StrikethroughSpan(), 0, display.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}
fun Int.convertToRedColor():SpannableString{
    val display = this.toString()
    val spannableString = SpannableString(display)
    //添加紅字體
    spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#cc1105")), 0, display.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}

