package com.crypto.currency.filters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.crypto.currency.filters.databinding.ActivityFilterBinding
import com.crypto.currency.ui.NumberInputFilter
import com.crypto.currency.ui.getTextValue
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
        setupView()
    }

    private fun setupView() {
        with(filterViewBinding) {
            val editTextsFilters = arrayOf(NumberInputFilter)
            rollingAverageEditText.filters = editTextsFilters
            timeSpanEditText.filters = editTextsFilters
            filterButton.setOnClickListener { view ->
                val timeSpan =
                    filterViewBinding.timeSpanEditText.getTextValue().ifBlank { "0" }.toInt()
                val rollingAverage =
                    filterViewBinding.rollingAverageEditText.getTextValue().ifBlank { "0" }.toInt()
                filterViewModel.addFilters(timeSpan, rollingAverage)
            }
        }

        filterViewModel.filterSaved.observe(this@FilterActivity, { filterSaved ->
            Toast.makeText(this@FilterActivity, filterSaved.toString(), LENGTH_SHORT).show()
            finish()
        })

        filterViewModel.errorState.observe(this@FilterActivity, { filterSaved ->
            finish()
        })

    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, FilterActivity::class.java)
        }
    }
}