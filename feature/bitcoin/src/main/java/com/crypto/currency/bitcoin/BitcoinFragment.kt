package com.crypto.currency.bitcoin

import android.util.Log
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinBinding
import com.crypto.currency.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BitcoinFragment : BaseFragment<BitcoinViewModel, FragmentBitcoinBinding>() {

    override val mViewModel: BitcoinViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinBinding {
        return FragmentBitcoinBinding.inflate(layoutInflater)
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