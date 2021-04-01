package com.crypto.currency.filters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currency.model.ActionResult
import com.crypto.currency.model.Failure
import com.crypto.currency.model.Loading
import com.crypto.currency.model.Success
import com.crypto.currency.model.chart.BitcoinFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val addBitcoinFilterUseCase: AddBitcoinFilterUseCase) :
    ViewModel() {

    private val _filterSaved = MutableLiveData<BitcoinFilter>()
    val filterSaved: LiveData<BitcoinFilter>
        get() = _filterSaved

    private val _loadingState = MutableLiveData<Loading>()
    val loadingState: LiveData<Loading>
        get() = _loadingState

    fun addFilters(timeSpan: Int, rollingAverage: Int) {
        _loadingState.value = Loading(true)
        viewModelScope.launch {
            updateUI(
                addBitcoinFilterUseCase.execute(
                    AddBitcoinFilterUseCase.Param(
                        BitcoinFilter(
                            timeSpan,
                            rollingAverage
                        )
                    )
                )
            )
        }
    }

    private fun updateUI(result: ActionResult<BitcoinFilter>) {
        when (result) {
            is Failure -> {
                Log.e("Failure", result.failure.message!!)
            }

            is Success -> {
                _filterSaved.value = result.data
            }
            else -> {
                Log.d("else", "else")
            }
        }
        _loadingState.value = Loading(false)
    }
}