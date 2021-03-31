package com.crypto.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crypto.currency.bitcoin.R
import com.crypto.currency.model.chart.ChartTypes
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        //TODO Extract
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_bottom_chart_one -> {
                    val bundle = bundleOf("chartName" to ChartTypes.TOTAL_BITCOINS.chartName)
                    val action = R.id.action_bitcoinFragment_self
                    navController.navigate(action, bundle)
                    true
                }
                R.id.navigation_bottom_chart_two -> {
                    val bundle = bundleOf("chartName" to ChartTypes.MARKET_PRICE.chartName)
                    val action = R.id.action_bitcoinFragment_self
                    navController.navigate(action, bundle)
                    true
                }
                R.id.navigation_bottom_chart_three -> {
                    val bundle = bundleOf("chartName" to ChartTypes.TRANSACTIONS_TOTAL.chartName)
                    val action = R.id.action_bitcoinFragment_self
                    navController.navigate(action, bundle)
                    true
                }
                else -> false
            }
        }
    }
}