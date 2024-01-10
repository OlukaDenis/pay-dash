package com.dennnytech.paydash.ui.linechart

import android.R.attr.fillColor
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.dennnytech.paydash.R
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentLineChartBinding
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LineChartFragment :
    BaseFragment<FragmentLineChartBinding>(FragmentLineChartBinding::inflate) {

    private val viewModel: LineChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureChart()
        observeData()
    }

    private fun observeData() {
        val values = ArrayList<Entry>()

        viewModel.data.observe(viewLifecycleOwner) {
            it.mapIndexed { index, model ->
                values.add(Entry(index.toFloat(), model.totalAmount.toFloat()))
            }

            drawChart(values)
        }
    }

    private fun configureChart() {
        with(binding) {
            chart.setBackgroundColor(Color.WHITE)
            chart.setGridBackgroundColor(fillColor)
            chart.setDrawGridBackground(true)

            chart.setDrawBorders(true)

            chart.description.isEnabled = false

            chart.setPinchZoom(false)

            val l = chart.legend
            l.isEnabled = false

            val xAxis = chart.xAxis
            xAxis.isEnabled = false

            val leftAxis = chart.axisLeft
//            leftAxis.axisMaximum = 900f
//            leftAxis.axisMinimum = -250f
            leftAxis.setDrawAxisLine(false)
            leftAxis.setDrawZeroLine(false)
            leftAxis.setDrawGridLines(false)

            chart.axisRight.isEnabled = false

            chart.invalidate()
        }
    }

    private fun drawChart(values: List<Entry>) {
        with(binding) {

            val set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.axisDependency = YAxis.AxisDependency.LEFT
            set1.color = ContextCompat.getColor(requireContext(), R.color.blue_80)
            set1.setDrawCircles(false)
            set1.lineWidth = 2f
            set1.circleRadius = 3f
            set1.fillAlpha = 255
            set1.setCircleColor(Color.WHITE);
            set1.setDrawFilled(true)
            set1.fillColor = ContextCompat.getColor(requireContext(), R.color.blue_80)
            set1.setDrawCircleHole(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider ->
                    chart.axisLeft.axisMinimum
                }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1)

            // create a data object with the data sets
            val data = LineData(dataSets)
            data.setDrawValues(false)

            // set data
            chart.data = data

        }
    }
}