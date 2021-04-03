package com.crypto.currency.bitcoin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.crypto.currency.bitcoin.databinding.FragmentBitcoinChartBinding
import com.crypto.currency.di.network.NetworkReceiver
import com.crypto.currency.model.chart.BitcoinChart
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.*
import com.crypto.currency.ui.databinding.BarChartItemBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class BitcoinChartFragment : BaseFragment<BitcoinChartViewModel, FragmentBitcoinChartBinding>() {

    @Inject
    lateinit var networkReceiver: NetworkReceiver

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
            val lastMonth = adapter.getLastMonth()
            mViewBinding.barchartCustom.root.removeAllViews()
            mViewBinding.barchartCustom.run {
                lastMonth.forEach { triple ->
                    val item = BarChartItemBinding.inflate(layoutInflater, this.root, false)
                    item.barShape.layoutParams.height = triple.first
                    item.barLabel.text = triple.second
                    item.barLabelDate.text = triple.third
                    this.root.addView(item.root)
                }
                this.barCustomChart.invalidate()
            }
            mViewBinding.chartName.text = bitcoinChart.name
            mViewBinding.chartDescription.text = bitcoinChart.description
            mViewBinding.legendInfo.text = resources.getString(
                R.string.barinfo_legend,
                chartType.chartName.toUpperCase(Locale.getDefault()),
                bitcoinChart.unit,
                bitcoinChart.period
            )
            mViewBinding.legendInfo.show()
            mViewBinding.legendBox.show()
            mViewBinding.barchartCustom.barCustomChart.show()
        })

        mViewModel.loadingState.observe(this, { state ->
            if (state.isLoading) {
                mViewBinding.contentLoadingProgressBar.show()
            } else {
                mViewBinding.contentLoadingProgressBar.hide()
            }
        })

        mViewModel.errorState.observe(this, { state ->
            mViewBinding.chartName.text = getString(R.string.check_your_connection)
            mViewBinding.chartDescription.text = getString(R.string.unable_to_connect)
            mViewBinding.legendInfo.gone()
            mViewBinding.legendBox.gone()
            mViewBinding.barchartCustom.barCustomChart.gone()
        })

        networkReceiver.wifiAvailable.observe(this,
            { isConnected ->
                if (isConnected) {
                    mViewBinding.contentLoadingProgressBar.changeVisibility()
                    mViewModel.getCharByName(chartType)
                }
            })
    }
}