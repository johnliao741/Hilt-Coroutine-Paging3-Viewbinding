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


fun String.toStrikePriceString():SpannableString{
    val spannableString = SpannableString(this)
    //添加中划线
    var start = 0
    if(this[0] == '$'){
        start = 1
    }
    spannableString.setSpan(StrikethroughSpan(), start, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}


