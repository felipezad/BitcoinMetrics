package com.crypto.currency.bitcoin

import android.util.Log
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BitcoinChartFragment : BaseFragment<BitcoinChartViewModel, FragmentBitcoinChartBinding>() {

    override val mViewModel: BitcoinChartViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinChartBinding {
        return FragmentBitcoinChartBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        //TODO Change
        Log.d("setupViewModel", "setupViewModel")
    }

    override fun setupView() {
        //TODO Change
        Log.d("setupViewModel", "setupViewModel")
    }
}