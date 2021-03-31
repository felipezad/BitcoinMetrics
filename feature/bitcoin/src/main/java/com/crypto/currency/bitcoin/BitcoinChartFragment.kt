package com.crypto.currency.bitcoin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.di.mapper.EpochFormatter
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BaseFragment
import com.crypto.currency.ui.BundleKey
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BitcoinChartFragment : BaseFragment<BitcoinChartViewModel, FragmentBitcoinChartBinding>() {

    override val mViewModel: BitcoinChartViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinChartBinding {
        return FragmentBitcoinChartBinding.inflate(layoutInflater)
    }

    private var charType: ChartTypes = ChartTypes.TOTAL_BITCOINS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.getString(BundleKey.CHART_NAME.key)?.let {
            charType = ChartTypes.from(it)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun startViewModel() {
        //TODO Change
        Log.d("startViewModel", "startViewModel")
        mViewModel.getCharByName(charType)
    }

    override fun setupView() {
        //TODO Change
        Log.d("setupView", "setupView")
        mViewModel.bitcoinChartData.observe(this, { bitcoinChart: BitcoinChart ->
            bitcoinChart.values.take(5).mapTo(mutableListOf<Entry>(), { value ->
                Entry(value.x.toFloat(), value.y.toFloat())
            }).also {
                val lineDataset = LineDataSet(it, charType.name)
                val lineData = LineData(lineDataset)

                mViewBinding.lineChart.run {
                    xAxis.position = XAxisPosition.BOTTOM
                    xAxis.granularity = 1f // only intervals of 1 day
                    xAxis.labelCount = 7
                    xAxis.valueFormatter = EpochFormatter
                    data = lineData

                    legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                    legend.orientation = Legend.LegendOrientation.HORIZONTAL
                    legend.setDrawInside(false)
                    legend.form = LegendForm.SQUARE
                    legend.formSize = 9f
                    legend.textSize = 11f
                    legend.xEntrySpace = 4f

                    invalidate()
                }
            }
            var count = 1.0f
            bitcoinChart.values.take(5).mapTo(mutableListOf(), { value ->
                BarEntry(++count, value.y.toFloat())
            }).also {
                val barDataSet = BarDataSet(it, charType.name)

                val barData = BarData(barDataSet)
                barData.dataSetLabels
                barData.barWidth = 0.9f

                mViewBinding.barChart.run {

                    xAxis.position = XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    xAxis.granularity = 1f // only intervals of 1 day
                    xAxis.labelCount = 7
                    xAxis.valueFormatter = EpochFormatter

                    data = barData
                    setFitBars(true)
                    invalidate()
                }
            }
        })

        mViewModel.loadingState.observe(this, { state ->
            if (state.isLoading) {
                mViewBinding.contentLoadingProgressBar.show()
            } else {
                mViewBinding.contentLoadingProgressBar.hide()
            }
        })
    }

}