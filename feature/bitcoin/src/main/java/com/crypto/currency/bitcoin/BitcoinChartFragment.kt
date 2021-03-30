package com.crypto.currency.bitcoin

import android.util.Log
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.di.mapper.EpochFormatter
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BaseFragment
import com.github.mikephil.charting.animation.Easing
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

    override fun startViewModel() {
        //TODO Change
        Log.d("startViewModel", "startViewModel")
        mViewModel.getCharByName(ChartTypes.TRANSACTIONS_PER_SECOND)
    }

    override fun setupView() {
        //TODO Change
        Log.d("setupView", "setupView")
        mViewModel.bitcoinChartData.observe(this, { bitcoinChart: BitcoinChart ->
            bitcoinChart.values.take(5).mapTo(mutableListOf<Entry>(), { value ->
                Entry(value.x.toFloat(), value.y.toFloat())
            }).also {
                val lineDataset = LineDataSet(it, ChartTypes.TRANSACTIONS_PER_SECOND.name)
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
            bitcoinChart.values.take(5).mapTo(mutableListOf(), { value ->
                BarEntry(value.x.toFloat(), value.y.toFloat())
            }).also {
                val barDataSet = BarDataSet(it, ChartTypes.TOTAL_BITCOINS.name)
                val barData = BarData(barDataSet)
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
            bitcoinChart.values.take(5).mapTo(mutableListOf(), { value ->
                PieEntry(value.y.toFloat())
            }).also {
                val pieDataSet = PieDataSet(it, ChartTypes.TRANSACTIONS_TOTAL.name)
                val pieData = PieData(pieDataSet)

                mViewBinding.pieChart.run {
                    animateY(1400, Easing.EaseInOutQuad)
                    data = pieData
                    invalidate()
                }
            }
        })

        mViewBinding.bitcoinScrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                println(
                    "ScrollX $scrollX -> ScrollY $scrollY"
                )
            })
    }

}