package tw.personal.jehuty.fsdation.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import tw.personal.jehuty.fsdation.util.autoCleared
import tw.personal.jehuty.fsdation.view.createProgressDialog
import tw.personal.jehuty.fsdation.view.hideKeyboard


abstract class AppFragment(layoutId: Int) : Fragment(layoutId) {

    private var progressDialog: AlertDialog by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = requireContext().createProgressDialog()
    }

    fun showProgressDialog() = progressDialog.show()

    fun dismissProgressDialog() = progressDialog.dismiss()

    fun hideSoftInput() {
        activity?.currentFocus?.let { activity?.hideKeyboard(it) }
    }

    override fun onDestroyView() {
        hideSoftInput()
        super.onDestroyView()
    }
}
