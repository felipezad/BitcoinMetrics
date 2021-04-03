package com.crypto.currency

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crypto.currency.bitcoin.R
import com.crypto.currency.di.network.NetworkReceiver
import com.crypto.currency.model.chart.ChartTypes
import com.crypto.currency.ui.BundleKey
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var featureNavigator: FeaturesNavigator

    @Inject
    lateinit var networkReceiver: NetworkReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupNetworkManagement()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(networkReceiver)
    }

    override fun onBackPressed() {
        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        val selectedItemId = bottomNavigationView.selectedItemId
        if (R.id.navigation_filters != selectedItemId) {
            bottomNavigationView.selectedItemId = R.id.navigation_filters
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_filters -> featureNavigator.navigateToFilters(this)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNetworkManagement() {
        this.registerReceiver(
            networkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        networkReceiver.wifiAvailable.observe(this, { isConnected ->
            if (isConnected == false) {
                featureNavigator.navigateToError(this)
            }
        })
    }

    private fun setupView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        setupBottomNavigation(navView, navController)

    }

    private fun setupBottomNavigation(navView: BottomNavigationView, navController: NavController) {
        navView.setOnNavigationItemSelectedListener { item ->
            val nextMetrics = R.id.action_bitcoinFragment_self
            when (item.itemId) {
                R.id.navigation_bottom_chart_one -> {
                    navController.navigate(
                        nextMetrics,
                        bundleOf(BundleKey.CHART_NAME.key to ChartTypes.TOTAL_BITCOINS.chartName)
                    )
                    true
                }
                R.id.navigation_bottom_chart_two -> {
                    navController.navigate(
                        nextMetrics,
                        bundleOf(BundleKey.CHART_NAME.key to ChartTypes.MARKET_PRICE.chartName)
                    )
                    true
                }
                R.id.navigation_bottom_chart_three -> {
                    navController.navigate(
                        nextMetrics,
                        bundleOf(BundleKey.CHART_NAME.key to ChartTypes.TRANSACTIONS_TOTAL.chartName)
                    )
                    true
                }
                else -> false
            }
        }
    }
}