package com.dennnytech.paydash.piechart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentPieChartBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF


class PieChartFragment : BaseFragment<FragmentPieChartBinding>(FragmentPieChartBinding::inflate) {

    private val parties = arrayOf(
        "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
        "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
        "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
        "Party Y", "Party Z"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureChart()
        drawData(12, 12f)
    }

    private fun configureChart() {
       with(binding) {
           chart.setUsePercentValues(true)
           chart.getDescription().setEnabled(false)
           chart.setExtraOffsets(5f, 10f, 5f, 5f)

           chart.setDragDecelerationFrictionCoef(0.95f)

           chart.setDrawHoleEnabled(true)
           chart.setHoleColor(Color.WHITE)

           chart.setTransparentCircleColor(Color.WHITE)
           chart.setTransparentCircleAlpha(110)

           chart.setHoleRadius(58f)
           chart.setTransparentCircleRadius(61f)

           chart.setDrawCenterText(true)

           chart.setRotationAngle(0f)
           // enable rotation of the chart by touch
           // enable rotation of the chart by touch
           chart.setRotationEnabled(true)
           chart.setHighlightPerTapEnabled(true)


           // add a selection listener
//           chart.setOnChartValueSelectedListener(this)

           chart.animateY(1400, Easing.EaseInOutQuad)
           // chart.spin(2000, 0, 360);

           // chart.spin(2000, 0, 360);
           val l: Legend = chart.getLegend()
           l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
           l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
           l.orientation = Legend.LegendOrientation.VERTICAL
           l.setDrawInside(false)
           l.xEntrySpace = 7f
           l.yEntrySpace = 0f
           l.yOffset = 0f

           // entry label styling
           chart.setEntryLabelColor(Color.WHITE)
           chart.setEntryLabelTextSize(12f)
       }
    }

    private fun drawData(count: Int, range: Float) {
        with(binding) {
            val entries = ArrayList<PieEntry>()

            // NOTE: The order of the entries when being added to the entries array determines their position around the center of
            // the chart.
            for (i in 0 until count) {
                entries.add(
                    PieEntry(
                        (Math.random() * range + range / 5).toFloat(),
                        parties[i % parties.size],
                    )
                )
            }
            val dataSet = PieDataSet(entries, "Election Results")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            // add a lot of colors
            val colors = ArrayList<Int>()
            for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
            for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
            colors.add(ColorTemplate.getHoloBlue())
            dataSet.colors = colors
            //dataSet.setSelectionShift(0f);
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            chart.data = data

            // undo all highlights
            chart.highlightValues(null)
            chart.invalidate()
        }
    }
}