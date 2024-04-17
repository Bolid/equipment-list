package ru.export.testapp.core

import android.app.AlertDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ru.export.testapp.R

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected open fun handleEvent(event: Event) {
        when (event) {
            is ErrorEvent -> onError(event.errorMessage)
        }
    }

    private fun onError(errorMessage: String) {
        val alert = AlertDialog.Builder(requireContext())
            .setMessage(errorMessage)
            .setPositiveButton(getString(R.string.alert_dialog_btn_close)) { dialog, _ -> dialog.dismiss() }
        alert.show()
    }

}