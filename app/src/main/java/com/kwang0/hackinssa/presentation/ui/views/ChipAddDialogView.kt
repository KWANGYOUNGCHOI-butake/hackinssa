package com.kwang0.hackinssa.presentation.ui.views

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.helper.ValidHelper
import com.kwang0.hackinssa.helper.exception.TagException
import com.kwang0.hackinssa.helper.hideKeyboard
import com.kwang0.hackinssa.presentation.ui.extensions.ChipAddListener


class ChipAddDialogView(private val context: Context, private val chipAddListener: ChipAddListener) {

    private lateinit var chip_add_dialog: AlertDialog
    private lateinit var add_et: EditText
    private lateinit var confirm_btn: Button
    private lateinit var cancel_btn: Button

    fun getDialog(): AlertDialog {
        return chip_add_dialog
    }

    fun openPaymentDialog() {
        val chipAddDialogView: View = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.dialog_chip_add, null)
        val chipAddDialogBuilder = AlertDialog.Builder(chipAddDialogView.getContext())
        add_et = chipAddDialogView.findViewById(R.id.chip_add_et)
        confirm_btn = chipAddDialogView.findViewById(R.id.chip_add_confirm_btn)
        cancel_btn = chipAddDialogView.findViewById(R.id.chip_add_cancel_btn)
        chipAddDialogBuilder.setView(chipAddDialogView)

        confirm_btn.setOnClickListener { v ->
            context.hideKeyboard(v)
            try {
                if(!TextUtils.isEmpty(add_et.editableText.toString().trim())
                        && ValidHelper.isTagValid(add_et.editableText.toString().trim())) {
                    chipAddListener.onChipAdded(add_et.editableText.toString().trim())
                    chip_add_dialog.dismiss()
                }
            } catch (e: TagException) {
                Toast.makeText(context, context.getString(R.string.tag_add_fail), Toast.LENGTH_LONG).show()
            }
        }
        cancel_btn.setOnClickListener { v ->
            context.hideKeyboard(v)
            chip_add_dialog.dismiss()
        }

        chip_add_dialog = chipAddDialogBuilder.create()
        chip_add_dialog.setCancelable(false)
        chip_add_dialog.show()
    }
}