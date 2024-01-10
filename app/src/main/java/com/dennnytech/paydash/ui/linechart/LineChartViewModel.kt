package com.dennnytech.paydash.ui.linechart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dennnytech.paydash.mappers.TransactionUiMapper
import com.dennnytech.paydash.models.TransactionUiModel
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.usecases.transactions.FilterTransactionsUseCase
import com.dennytech.domain.usecases.transactions.GetCategoryAmountDataUseCase
import com.dennytech.domain.usecases.transactions.GetServiceAmountDataUseCase
import com.dennytech.domain.utils.Helpers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LineChartViewModel @Inject constructor(
    private val getCategoryAmountDataUseCase: GetCategoryAmountDataUseCase,
    private val filterTransactionsUseCase: FilterTransactionsUseCase,
    private val transactionUiMapper: TransactionUiMapper
): ViewModel() {

    val data: MutableLiveData<List<TransactionUiModel>> = MutableLiveData()
    val startDate = "2023-10-01 00:00:00"
    val endDate = "2023-10-01 23:59:59"

    init {
        onEvent(LineChartEvent.Init)
    }

    fun onEvent(event: LineChartEvent) {
        when (event) {
            is LineChartEvent.Init -> filter(startDate, endDate)
            is LineChartEvent.OneWeek -> filterForOneWeek()
            is LineChartEvent.TwoWeeks -> filterForTwoWeeks()
            is LineChartEvent.ThreeWeeks -> filterForThreeWeeks()
            is LineChartEvent.OneMonth -> filterForOneMonth()
        }
    }

    private fun filter(start: String, end: String) {
        data.value = runBlocking { filterTransactionsUseCase(
            FilterTransactionsUseCase.Param(
                startDate = start,
                endDate = end
            )
        ).first() }.map {
            transactionUiMapper.toUI(it)
        }
    }

    private fun filterForOneWeek() {
        val end = Helpers.addDaysToTimestamp(startDate, 7)
        filter(startDate, end)
    }

    private fun filterForTwoWeeks() {
        val end = Helpers.addDaysToTimestamp(startDate, 14)
        filter(startDate, end)
    }

    private fun filterForThreeWeeks() {
        val end = Helpers.addDaysToTimestamp(startDate, 21)
        filter(startDate, end)
    }

    private fun filterForOneMonth() {
        val end = Helpers.addDaysToTimestamp(startDate, 30)
        filter(startDate, end)
    }
}

sealed class LineChartEvent {
    data object Init : LineChartEvent()
    data object OneWeek : LineChartEvent()
    data object TwoWeeks : LineChartEvent()
    data object ThreeWeeks : LineChartEvent()
    data object OneMonth : LineChartEvent()
}