package com.crypto.currency.filters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.crypto.currency.filters.databinding.ActivityFilterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {

    private val filterViewModel: FilterViewModel by viewModels()
    private val filterViewBinding: ActivityFilterBinding by lazy {
        ActivityFilterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(filterViewBinding.root)
    }

    override fun onResume() {
        super.onResume()
        filterViewModel.addFilters(5, 8)
    }

    companion object {

        fun newInstance(context: Context): Intent {
            return Intent(context, FilterActivity::class.java)
        }
    }
}