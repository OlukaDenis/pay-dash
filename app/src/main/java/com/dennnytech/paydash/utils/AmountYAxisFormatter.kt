package com.dennnytech.paydash.utils

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ViewPortHandler
import java.text.DecimalFormat


class AmountYAxisFormatter: ValueFormatter() {

    private val mSuffix = arrayOf(
        "", "k", "m", "b", "t"
    )

    private val mMaxLength = 5
    private val mFormat = DecimalFormat("###E00")
    private val mText = ""

    override fun getPointLabel(entry: Entry): String {
        return entry.y.toDouble().let { makePretty(it) } + mText
    }

    /**
     * Formats each number properly. Special thanks to Roman Gromov
     * (https://github.com/romangromov) for this piece of code.
     */
    private fun makePretty(number: Double): String? {
        var r = mFormat.format(number)
        val numericValue1 = Character.getNumericValue(r[r.length - 1])
        val numericValue2 = Character.getNumericValue(r[r.length - 2])
        val combined = Integer.valueOf(numericValue2.toString() + "" + numericValue1)
        r = r.replace("E[0-9][0-9]".toRegex(), mSuffix[combined / 3])
        while (r.length > mMaxLength || r.matches("[0-9]+\\.[a-z]".toRegex())) {
            r = r.substring(0, r.length - 2) + r.substring(r.length - 1)
        }
        return r
    }
}