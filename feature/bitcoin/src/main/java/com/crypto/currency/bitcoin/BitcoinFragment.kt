package com.crypto.currency.bitcoin

import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinBinding
import com.crypto.currency.ui.BaseFragment


class BitcoinFragment : BaseFragment<BitcoinViewModel, FragmentBitcoinBinding>() {

    override val mViewModel: BitcoinViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinBinding {
        return FragmentBitcoinBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        TODO("Not yet implemented")
    }

    override fun setupView() {
        TODO("Not yet implemented")
    }
}