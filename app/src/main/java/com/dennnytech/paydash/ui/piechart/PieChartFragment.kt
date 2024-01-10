package com.dennnytech.paydash.ui.piechart

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import com.dennnytech.paydash.R
import com.dennnytech.paydash.base.BaseFragment
import com.dennnytech.paydash.databinding.FragmentPieChartBinding
import com.dennnytech.paydash.ui.linechart.LineChartViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PieChartFragment : BaseFragment<FragmentPieChartBinding>(FragmentPieChartBinding::inflate) {

    private val viewModel: PieChartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureChart()
        observeData()
        handleFilters()
    }

    private fun handleFilters() {
        with(binding) {
            acibFilterPie.setOnClickListener {
                val popupMenu = PopupMenu(requireContext(), acibFilterPie)
                popupMenu.menuInflater.inflate(R.menu.revenue_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.byCategory -> viewModel.onEvent(PieChartEvent.FilterByCategory)
                        R.id.byService -> viewModel.onEvent(PieChartEvent.FilterByService)
                        R.id.byType -> viewModel.onEvent(PieChartEvent.FilterByType)
                    }
                    true
                }

                popupMenu.show()
            }
        }
    }

    private fun observeData() {
        viewModel.data.observe(viewLifecycleOwner) {
            val entries = ArrayList<PieEntry>()

            it.mapIndexed { _, model ->
                entries.add(PieEntry(model.amount, model.label))
            }

            drawData(entries)
        }
    }

    private fun configureChart() {
        with(binding) {
            chart.setUsePercentValues(true)

            chart.description = buildDescription()
            chart.setExtraOffsets(5f, 10f, 5f, 5f)

            chart.dragDecelerationFrictionCoef = 0.95f
            chart.setTouchEnabled(true)

            chart.isDrawHoleEnabled = true
            chart.setHoleColor(Color.WHITE)

            chart.setTransparentCircleColor(Color.WHITE)
            chart.setTransparentCircleAlpha(110)

            chart.holeRadius = 58f
            chart.transparentCircleRadius = 61f

            chart.setDrawCenterText(true)

            chart.rotationAngle = 0f
            chart.isRotationEnabled = true
            chart.isHighlightPerTapEnabled = true


            chart.animateY(1400, Easing.EaseInOutQuad)

            val l: Legend = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(false)

            // entry label styling
            chart.setEntryLabelColor(Color.WHITE)
            chart.setEntryLabelTextSize(0f) // turn off labels
        }
    }

    private fun buildDescription(): Description {
        val description = Description()
        description.text = "Revenue Data"

        return description;
    }

    private fun drawData(entries: ArrayList<PieEntry>) {
        with(binding) {

            val dataSet = PieDataSet(entries, "")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            val colors = ArrayList<Int>()
            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

            dataSet.colors = colors
            dataSet.selectionShift = 0f;
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(0f) // turn off value text
            data.setValueTextColor(Color.WHITE)

            chart.data = data

            chart.highlightValues(null)
            chart.invalidate()
        }
    }
}