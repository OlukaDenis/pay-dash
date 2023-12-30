package com.dennnytech.paydash.barchart

import android.R
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentBarChartBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BarChartFragment : BaseFragment<FragmentBarChartBinding>(FragmentBarChartBinding::inflate) {
    private val viewModel: BarchartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(BarChartEvent.Init)

        with(binding) {
//            chart.setOnChartValueSelectedListener(this)
            chart.setDrawBarShadow(false)
            chart.setDrawValueAboveBar(true)
            chart.description.isEnabled = false
            chart.setMaxVisibleValueCount(60)
            chart.setPinchZoom(false)
            chart.setDrawGridBackground(false)

            val l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.form = LegendForm.SQUARE
            l.formSize = 9f
            l.textSize = 11f
            l.xEntrySpace = 4f
        }

        setData()
    }

    private fun setData() {
        with(binding) {
            val values = ArrayList<BarEntry>()

            viewModel.data.observe(requireActivity()) {
                Timber.d("Grouped: %s", Gson().toJson(it))

                it.mapIndexed { index, model ->
                    values.add(
                        BarEntry(
                            index.toFloat(),
                            model.totalAmount.toFloat()
                        )
                    )
                }

                val set1: BarDataSet
                if (chart.getData() != null &&
                    chart.getData().getDataSetCount() > 0
                ) {
                    set1 = chart.getData().getDataSetByIndex(0) as BarDataSet
                    set1.values = values
                    chart.getData().notifyDataChanged()
                    chart.notifyDataSetChanged()

                } else {
                    set1 = BarDataSet(values, "The year 2017")
                    set1.setDrawIcons(false)
                    val startColor1 =
                        ContextCompat.getColor(requireContext(), R.color.holo_orange_light)
                    val startColor2 =
                        ContextCompat.getColor(requireContext(), R.color.holo_blue_light)
                    val startColor3 =
                        ContextCompat.getColor(requireContext(), R.color.holo_orange_light)
                    val startColor4 =
                        ContextCompat.getColor(requireContext(), R.color.holo_green_light)
                    val startColor5 =
                        ContextCompat.getColor(requireContext(), R.color.holo_red_light)
                    val endColor1 = ContextCompat.getColor(requireContext(), R.color.holo_blue_dark)
                    val endColor2 = ContextCompat.getColor(requireContext(), R.color.holo_purple)
                    val endColor3 =
                        ContextCompat.getColor(requireContext(), R.color.holo_green_dark)
                    val endColor4 = ContextCompat.getColor(requireContext(), R.color.holo_red_dark)
                    val endColor5 =
                        ContextCompat.getColor(requireContext(), R.color.holo_orange_dark)

//            val gradientFills: MutableList<Fill> = ArrayList<Fill>()
//            gradientFills.add(Fill(startColor1, endColor1))
//            gradientFills.add(Fill(startColor2, endColor2))
//            gradientFills.add(Fill(startColor3, endColor3))
//            gradientFills.add(Fill(startColor4, endColor4))
//            gradientFills.add(Fill(startColor5, endColor5))
//            set1.setFills(gradientFills)

                    val dataSets = ArrayList<IBarDataSet>()
                    dataSets.add(set1)
                    val data = BarData(dataSets)
                    data.setValueTextSize(10f)
//                data.setValueTypeface(tfLight)
                    data.barWidth = 0.9f
                    chart.setData(data)
                }
            }
        }
    }
}