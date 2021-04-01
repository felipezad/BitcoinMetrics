package com.crypto.currency.filters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
    }

    companion object {

        fun newInstance(context: Context): Intent {
            return Intent(context, FilterActivity::class.java)
        }
    }
}