package com.crypto.currency.bitcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinBinding


class BitcoinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBitcoinBinding.inflate(inflater).root
    }

}