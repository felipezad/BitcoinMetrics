package com.crypto.currency.bitcoin

import android.util.Log
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BaseFragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BitcoinChartFragment : BaseFragment<BitcoinChartViewModel, FragmentBitcoinChartBinding>() {

    override val mViewModel: BitcoinChartViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinChartBinding {
        return FragmentBitcoinChartBinding.inflate(layoutInflater)
    }

    override fun startViewModel() {
        //TODO Change
        Log.d("startViewModel", "startViewModel")
        mViewModel.getCharByName(ChartTypes.TRANSACTIONS_PER_SECOND)
    }

    override fun setupView() {
        //TODO Change
        Log.d("setupView", "setupView")
        mViewModel.bitcoinChartData.observe(this, { bitcoinChart: BitcoinChart ->
            bitcoinChart.values.mapTo(mutableListOf<Entry>(), { value ->
                Entry(value.x.toFloat(), value.y.toFloat())
            }).also {
                val lineDataset = LineDataSet(it, ChartTypes.TRANSACTIONS_PER_SECOND.name)
                val lineData = LineData(lineDataset)

                mViewBinding.lineChart.run {
                    data = lineData
                    invalidate()
                }
            }
        })


    }
}