package com.crypto.currency.bitcoin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BaseFragment
import com.crypto.currency.ui.BundleKey
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
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
            var xAx = 1f
            var yAx = 1f
            val listSize = bitcoinChart.values.size
            bitcoinChart.values.subList(listSize - 7, listSize)
                .mapTo(mutableListOf<Entry>(), { value ->
                    Entry(xAx++, value.y.toFloat())
                }).also {
                    val lineDataset = LineDataSet(it, charType.name)
                    val lineData = LineData(lineDataset)


                    mViewBinding.lineChart.run {
                        xAxis.position = XAxisPosition.BOTTOM
                        xAxis.granularity = 1f
                        xAxis.labelCount = 7
                        axisRight.isEnabled = false

                        axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                        axisLeft.setDrawLabels(false)

                        description.text = ""

                        data = lineData

                        invalidate()
                    }
                }

            bitcoinChart.values.subList(listSize - 7, listSize).mapTo(mutableListOf(), { value ->
                BarEntry(xAx++, value.y.toFloat())
            }).also {
                val barDataSet = BarDataSet(it, charType.name)

                val barData = BarData(barDataSet)
                barData.dataSetLabels
                barData.barWidth = 0.9f

                mViewBinding.barChart.run {
                    setFitBars(true)
                    xAxis.position = XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    xAxis.granularity = 1f
                    xAxis.setDrawLabels(false)
                    description.text = ""


                    data = barData
                    setFitBars(true)

                    axisLeft.setDrawLabels(false) // no axis labels
                    axisLeft.setDrawGridLines(false) // no grid lines
                    axisLeft.setDrawZeroLine(true) // draw a zero line
                    axisRight.isEnabled = false


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