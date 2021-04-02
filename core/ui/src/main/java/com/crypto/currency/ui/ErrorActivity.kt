package com.crypto.currency.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ErrorActivity::class.java)
        }
    }
}