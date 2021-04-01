package com.crypto.currency.ui

import android.text.InputFilter
import android.text.Spanned


object NumberInputFilter : InputFilter {

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString())
            if (isNumberInputFilterValid(input)) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isNumberInputFilterValid(input: String): Boolean {
        return input.toInt() in 1..12
    }
}