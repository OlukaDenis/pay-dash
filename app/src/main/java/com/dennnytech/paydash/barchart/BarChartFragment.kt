package com.dennnytech.paydash.barchart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentBarChartBinding
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class BarChartFragment : BaseFragment<FragmentBarChartBinding>(FragmentBarChartBinding::inflate) {
    private val viewModel: BarchartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("Onviewcreated...")

        configureChart()

//        viewModel.data.observe(viewLifecycleOwner) {
//            val combinedData = CombinedData()
//            combinedData.setData(generateLineData(it))
//            combinedData.setData(generateBarchartData(it))
//
//            with(binding) {
//                chart.data = combinedData
//                chart.invalidate()
//            }
//        }

        setData()
    }

    private fun configureChart() {
        with(binding) {
            //            chart.setOnChartValueSelectedListener(this)
            chart.setDrawBarShadow(true)
            chart.setDrawValueAboveBar(true)
            chart.description.isEnabled = true
            chart.setMaxVisibleValueCount(60)
            chart.setPinchZoom(false)
            chart.setDrawGridBackground(true)

            val l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.form = LegendForm.SQUARE
            l.formSize = 9f
            l.textSize = 11f
            l.xEntrySpace = 4f

            val xAxis = chart.xAxis
            xAxis.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            val leftAxis: YAxis = chart.axisLeft
//            leftAxis.typeface = tfLight
            leftAxis.setLabelCount(6, false)
            leftAxis.axisMinimum = -2.5f
            leftAxis.axisMaximum = 2.5f
            leftAxis.isGranularityEnabled = true
            leftAxis.granularity = 0.1f

            val rightAxis: YAxis = chart.axisRight
            rightAxis.setDrawGridLines(false)
//            rightAxis.typeface = tfLight
            rightAxis.setLabelCount(6, false)
            rightAxis.axisMinimum = -2.5f
            rightAxis.axisMaximum = 2.5f
            rightAxis.granularity = 0.1f
        }
    }

    private fun generateLineData(list: List<ServiceAmountDomainModel>): LineData {
        val d = LineData()
        val entries = ArrayList<Entry>()

        list.mapIndexed { index, model ->
            entries.add(
                Entry(
                    index.toFloat(),
                    model.totalAmount.toFloat()
                )
            )
        }

        val set = LineDataSet(entries, "Line DataSet")
        set.color = Color.BLUE
        set.lineWidth = 2.5f
        set.setCircleColor(Color.rgb(240, 238, 70))
        set.circleRadius = 5f
        set.fillColor = Color.rgb(240, 238, 70)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 10f
        set.valueTextColor = Color.rgb(240, 238, 70)
        set.axisDependency = YAxis.AxisDependency.LEFT
        d.addDataSet(set)

        return d
    }

    private fun generateBarchartData(list: List<ServiceAmountDomainModel>): BarData {

        val values = ArrayList<BarEntry>()
        val dataSets = ArrayList<IBarDataSet>()

        list.mapIndexed { index, model ->
            values.add(
                BarEntry(
                    index.toFloat(),
                    model.totalAmount.toFloat()
                )
            )
        }

        val set1 = BarDataSet(values, "Transactions by Service")
        set1.color = Color.rgb(60, 220, 78)
        set1.setDrawIcons(false)
        dataSets.add(set1)

        val data = BarData(dataSets)
        data.setValueTextSize(10f)
        data.barWidth = 0.9f

        return data

    }

    private fun setData() {
        with(binding) {

            viewModel.data.observe(requireActivity()) {
                val values = ArrayList<BarEntry>()
                val dataSets = ArrayList<IBarDataSet>()

                it.mapIndexed { index, model ->
                    values.add(
                        BarEntry(
                            index.toFloat(),
                            model.totalAmount.toFloat()
                        )
                    )
                }

                val set1 = BarDataSet(values, "Transactions by Service")
                set1.color = Color.rgb(60, 220, 78)
                set1.setDrawIcons(false)
                dataSets.add(set1)

                val data = BarData(dataSets)
                data.setValueTextSize(10f)
                data.barWidth = 0.9f
                chart.data = data

                chart.data.notifyDataChanged()
                chart.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("Barchart onResume...")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("Barchart onPause")
    }
}