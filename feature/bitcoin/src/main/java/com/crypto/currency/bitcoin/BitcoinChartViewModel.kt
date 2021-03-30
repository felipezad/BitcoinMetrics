package com.crypto.currency.bitcoin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Loading
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitcoinChartViewModel @Inject constructor(
    private val getChartByNameUseCase: GetChartByNameUseCase
) : ViewModel() {

    private val _bitcoinChartData = MutableLiveData<BitcoinChart>()
    val bitcoinChartData: LiveData<BitcoinChart>
        get() = _bitcoinChartData

    fun getCharByName(chartName: ChartTypes) {
        viewModelScope.launch {
            notifyUI(getChartByNameUseCase.execute(GetChartByNameUseCase.Param(chartName.graphName)))
        }
    }

    private fun notifyUI(result: ActionResult<BitcoinChart>) {
        when (result) {
            is Failure -> Log.e("Failure", result.failure.message!!)
            is Loading -> Log.d("Loading", "Loading")
            is Success -> _bitcoinChartData.value = result.data
        }
    }
}