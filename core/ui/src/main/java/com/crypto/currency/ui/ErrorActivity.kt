package com.crypto.currency.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crypto.currency.di.network.NetworkReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ErrorActivity : AppCompatActivity() {

    @Inject
    lateinit var networkReceiver: NetworkReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        networkReceiver.wifiAvailable.observe(this, { isConnected ->
            if (isConnected) {
                this@ErrorActivity.finish()
            }
        })
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ErrorActivity::class.java)
        }
    }
}