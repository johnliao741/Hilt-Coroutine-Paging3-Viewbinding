package tw.personal.jehuty.fsdation.view

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog


import com.google.android.material.dialog.MaterialAlertDialogBuilder
import tw.personal.jehuty.fsdation.R
import tw.personal.jehuty.fsdation.databinding.DialogDatePickerBinding
import tw.personal.jehuty.fsdation.databinding.DialogProgressBinding
import java.time.LocalDate

fun Context.showMessageDialog(
    messageId: Int,
    onNegativeListener: DialogInterface.OnClickListener? = null,
    onPositiveListener: DialogInterface.OnClickListener? = null
) = showMessageDialog(getString(messageId), null, onNegativeListener, onPositiveListener)

fun Context.showMessageDialog(
    message: String,
    titleId: Int,
    onNegativeListener: DialogInterface.OnClickListener? = null,
    onPositiveListener: DialogInterface.OnClickListener? = null
) = showMessageDialog(message, getString(titleId), onNegativeListener, onPositiveListener)

fun Context.showMessageDialog(
    message: String,
    title: String? = null,
    onNegativeListener: DialogInterface.OnClickListener? = null,
    onPositiveListener: DialogInterface.OnClickListener? = null
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.ok, onPositiveListener)
        .setCancelable(false)
        .also { builder ->
            onNegativeListener?.let { builder.setNegativeButton(R.string.cancel, it) }
        }
        .show()
}



fun Context.createProgressDialog(resId: Int = R.string.loading): AlertDialog =
    MaterialAlertDialogBuilder(this)
        .setView(
            DialogProgressBinding.inflate(LayoutInflater.from(this))
                .apply { text.setText(resId) }
                .root
        )
        .setCancelable(false)
        .create()
