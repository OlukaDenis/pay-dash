package com.dennnytech.paydash

import android.graphics.Color
import android.graphics.DashPathEffect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.dennnytech.paydash.ui.barchart.BarChartEvent
import com.dennnytech.paydash.ui.barchart.BarchartViewModel
import com.dennnytech.paydash.databinding.ActivityMainBinding
import com.dennnytech.paydash.ui.linechart.LineChartViewModel
import com.dennytech.domain.utils.Helpers
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Date

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate....")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onEvent(HomeEvent.Init)

        viewModel.remoteLoading.observe(this) {
            binding.clLoading.isVisible = it
        }

//        val d = Helpers.addDaysToDate("2023-10-01 00:00:00", 5)
//        Timber.d("Added days: %s", Date(d))
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume...")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause...")
    }

}