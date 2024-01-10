package com.dennnytech.paydash.ui.linechart

import android.R.attr.fillColor
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.dennnytech.paydash.R
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentLineChartBinding
import com.dennnytech.paydash.utils.AmountYAxisFormatter
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LineChartFragment :
    BaseFragment<FragmentLineChartBinding>(FragmentLineChartBinding::inflate) {

    private val viewModel: LineChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        handleFilters()
    }

    private fun observeData() {
        val values = ArrayList<Entry>()

        viewModel.data.observe(viewLifecycleOwner) {
            Timber.d("Transactions: %s", it.size)
            it.mapIndexed { index, model ->
                values.add(Entry(model.date.toFloat(), model.amount.toFloat()))
            }

            configureChart()
            drawChart(values)
        }
    }

    private fun configureChart() {
        with(binding) {
            chart.setBackgroundColor(Color.WHITE)
            chart.setGridBackgroundColor(fillColor)
            chart.setDrawGridBackground(true)
            chart.setBorderColor(Color.WHITE)
            chart.isAutoScaleMinMaxEnabled = true

            chart.setDrawBorders(true)

            chart.description.isEnabled = false
            chart.setNoDataText(getString(R.string.empty_data))

            chart.setPinchZoom(false)

            val l = chart.legend
            l.isEnabled = false

            val xAxis = chart.xAxis
            xAxis.isEnabled = false
            xAxis.axisLineColor = Color.WHITE

            val leftAxis = chart.axisLeft
            leftAxis.setDrawAxisLine(false)
            leftAxis.setDrawZeroLine(false)
            leftAxis.setDrawGridLines(false)
//            leftAxis.valueFormatter = AmountYAxisFormatter()
            leftAxis.textColor = Color.GRAY
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            leftAxis.axisLineColor = Color.WHITE

            chart.axisRight.isEnabled = false

            chart.invalidate()
        }
    }

    private fun handleFilters() {
        with(binding) {
            acibFilterLine.setOnClickListener {
                val popupMenu = PopupMenu(requireContext(), acibFilterLine)
                popupMenu.menuInflater.inflate(R.menu.transaction_period_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.byOneWeek -> {
                            resetChart()
                            viewModel.onEvent(LineChartEvent.OneWeek)
                        }

                        R.id.byTwoWeek -> {
                            resetChart()
                            viewModel.onEvent(LineChartEvent.TwoWeeks)
                        }

                        R.id.byThreeWeek -> {
                            resetChart()
                            viewModel.onEvent(LineChartEvent.ThreeWeeks)
                        }

                        R.id.byOnMonth -> {
                            resetChart()
                            viewModel.onEvent(LineChartEvent.OneMonth)
                        }
                    }
                    true
                }

                popupMenu.show()
            }
        }
    }

    private fun drawChart(values: List<Entry>) {
        with(binding) {

            val set = LineDataSet(values, getString(R.string.transactions_by_period))
            set.mode = LineDataSet.Mode.CUBIC_BEZIER
            set.cubicIntensity = 0.2f
            set.axisDependency = YAxis.AxisDependency.LEFT
            set.color = ContextCompat.getColor(requireContext(), R.color.blue_80)
            set.setDrawCircles(false)
            set.lineWidth = 2f
            set.circleRadius = 3f
            set.fillAlpha = 255
            set.setCircleColor(Color.WHITE)
            set.setDrawFilled(true)
            set.fillColor = ContextCompat.getColor(requireContext(), R.color.blue_80)
            set.setDrawCircleHole(true)
            set.fillFormatter =
                IFillFormatter { dataSet, dataProvider ->
                    chart.axisLeft.axisMinimum
                }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set)

            // create a data object with the data sets
            val data = LineData(dataSets)
            data.setDrawValues(false)

            // set data
            chart.data = data

            chart.data.notifyDataChanged();
            chart.notifyDataSetChanged()
        }
    }

    private fun resetChart() {
       with(binding) {
           chart.fitScreen()
           chart.data?.clearValues()
           chart.clear()

//           chart.xAxis.valueFormatter = null
//
//           val yAxisLeft = chart.axisLeft
//           val yAxisRight = chart.axisRight
//
//           yAxisLeft.resetAxisMinimum()
//           yAxisLeft.resetAxisMaximum()
//
//           yAxisRight.resetAxisMinimum()
//           yAxisRight.resetAxisMaximum()

           chart.notifyDataSetChanged()

           chart.invalidate()
       }
    }
}