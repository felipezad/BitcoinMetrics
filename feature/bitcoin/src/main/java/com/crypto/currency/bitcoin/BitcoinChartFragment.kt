package com.crypto.currency.bitcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BaseFragment
import com.crypto.currency.ui.BitcoinChartAdapter
import com.crypto.currency.ui.BundleKey
import com.crypto.currency.ui.changeVisibility
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class BitcoinChartFragment : BaseFragment<BitcoinChartViewModel, FragmentBitcoinChartBinding>() {

    override val mViewModel: BitcoinChartViewModel by viewModels()

    override fun getViewBinding(): FragmentBitcoinChartBinding {
        return FragmentBitcoinChartBinding.inflate(layoutInflater)
    }

    private var chartType: ChartTypes = ChartTypes.TOTAL_BITCOINS

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.getString(BundleKey.CHART_NAME.key)?.let {
            chartType = ChartTypes.from(it)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getCharByName(chartType)
    }

    override fun setupView() {
        mViewModel.bitcoinChartData.observe(this, { bitcoinChart: BitcoinChart ->
            val adapter = BitcoinChartAdapter(bitcoinChart)
            val lastFive = adapter.getLastFiveValues()
            mViewBinding.barchartCustom.run {
                firstBarchart.barLabel.text = lastFive[0].second
                firstBarchart.barShape.layoutParams.height = lastFive[0].first

                secondBarchart.barLabel.text = lastFive[1].second
                secondBarchart.barShape.layoutParams.height = lastFive[1].first

                thirdBarchart.barLabel.text = lastFive[2].second
                thirdBarchart.barShape.layoutParams.height = lastFive[2].first

                fourthBarchart.barLabel.text = lastFive[3].second
                fourthBarchart.barShape.layoutParams.height = lastFive[3].first

                fifthBarchart.barLabel.text = lastFive[4].second
                fifthBarchart.barShape.layoutParams.height = lastFive[4].first

                this.barCustomChart.invalidate()
            }
            mViewBinding.chartName.text = bitcoinChart.name
            mViewBinding.chartDescription.text = bitcoinChart.description
            mViewBinding.legendInfo.text =
                "${chartType.chartName.toUpperCase(Locale.getDefault())} (${bitcoinChart.unit}/${bitcoinChart.period})"

        })

        mViewModel.loadingState.observe(this, { state ->
            if (state.isLoading) {
                mViewBinding.contentLoadingProgressBar.show()
                mViewBinding.barchartCustom.barCustomChart.changeVisibility()
                mViewBinding.legendInfo.changeVisibility()
                mViewBinding.legendBox.changeVisibility()
            } else {
                mViewBinding.contentLoadingProgressBar.hide()
                mViewBinding.barchartCustom.barCustomChart.changeVisibility()
                mViewBinding.legendInfo.changeVisibility()
                mViewBinding.legendBox.changeVisibility()
            }
        })
    }
}