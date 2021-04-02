package com.crypto.currency.ui

import android.view.View
import android.widget.EditText

fun EditText.getTextValue() = this.text.toString()

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.changeVisibility() {
    if (this.visibility == View.VISIBLE) {
        gone()
    } else {
        show()
    }
}
