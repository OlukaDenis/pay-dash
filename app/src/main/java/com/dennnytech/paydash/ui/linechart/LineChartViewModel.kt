package com.dennnytech.paydash.ui.linechart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dennytech.domain.models.CategoryAmountDomainModel
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.usecases.transactions.GetCategoryAmountDataUseCase
import com.dennytech.domain.usecases.transactions.GetServiceAmountDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LineChartViewModel @Inject constructor(
    private val getCategoryAmountDataUseCase: GetCategoryAmountDataUseCase
): ViewModel() {

    val data: MutableLiveData<List<CategoryAmountDomainModel>> = MutableLiveData()
    val denis = runBlocking { getCategoryAmountDataUseCase().first() }

    init {
        initialize()
    }

    fun onEvent(event: LineChartEvent) {
        when (event) {
            is LineChartEvent.Init -> initialize()
        }
    }

    private fun initialize() {
        data.value = runBlocking { getCategoryAmountDataUseCase().first() }
    }
}

sealed class LineChartEvent {
    data object Init : LineChartEvent()
}