package com.crypto.currency.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currency.model.chart.BitcoinFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val addFilterUseCase: AddFilterUseCase) :
    ViewModel() {

    fun addFilters(param: BitcoinFilter) {
        viewModelScope.launch {
            addFilterUseCase.execute(param)
        }
    }
}