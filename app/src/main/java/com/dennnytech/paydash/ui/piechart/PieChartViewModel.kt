package com.dennnytech.paydash.ui.piechart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dennnytech.paydash.mappers.CategoryAmountToRevenueMapper
import com.dennnytech.paydash.mappers.ServiceAmountToRevenueMapper
import com.dennnytech.paydash.mappers.TypeAmountToRevenueMapper
import com.dennnytech.paydash.models.RevenueUiModel
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.usecases.transactions.GetCategoryAmountDataUseCase
import com.dennytech.domain.usecases.transactions.GetServiceAmountDataUseCase
import com.dennytech.domain.usecases.transactions.GetTypeAmountDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class PieChartViewModel @Inject constructor(
    private val getCategoryAmountDataUseCase: GetCategoryAmountDataUseCase,
    private val getServiceAmountDataUseCase: GetServiceAmountDataUseCase,
    private val getTypeAmountDataUseCase: GetTypeAmountDataUseCase,
    private val categoryAmountToRevenueMapper: CategoryAmountToRevenueMapper,
    private val serviceAmountToRevenueMapper: ServiceAmountToRevenueMapper,
    private val typeAmountToRevenueMapper: TypeAmountToRevenueMapper
): ViewModel() {

    val data: MutableLiveData<List<RevenueUiModel>> = MutableLiveData()

    init {
        onEvent(PieChartEvent.Init)
    }

    fun onEvent(event: PieChartEvent) {
        when (event) {
            is PieChartEvent.Init -> getRevenueByCategory()
            is PieChartEvent.FilterByCategory -> getRevenueByCategory()
            is PieChartEvent.FilterByService -> getRevenueByService()
            is PieChartEvent.FilterByType -> getRevenueByType()
        }
    }

    private fun getRevenueByCategory() {
        data.value = runBlocking { getCategoryAmountDataUseCase().first() }.map {
            categoryAmountToRevenueMapper.toUI(it)
        }
    }

    private fun getRevenueByService() {
        data.value = runBlocking { getServiceAmountDataUseCase().first() }.map {
            serviceAmountToRevenueMapper.toUI(it)
        }
    }

    private fun getRevenueByType() {
        data.value = runBlocking { getTypeAmountDataUseCase().first() }.map {
            typeAmountToRevenueMapper.toUI(it)
        }
    }
}

sealed class PieChartEvent {
    data object Init : PieChartEvent()
    data object FilterByCategory: PieChartEvent()
    data object FilterByType: PieChartEvent()
    data object FilterByService: PieChartEvent()
}