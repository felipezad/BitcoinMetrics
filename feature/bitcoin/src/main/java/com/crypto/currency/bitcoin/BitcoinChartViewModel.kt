package com.crypto.currency.bitcoin

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BitcoinChartViewModel @Inject constructor(
    private val getCharUseCase: GetCharUseCase
) : ViewModel() {
}