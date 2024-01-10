package com.dennnytech.paydash.ui.barchart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.usecases.transactions.GetServiceAmountDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BarchartViewModel @Inject constructor(
    private val getServiceAmountDataUseCase: GetServiceAmountDataUseCase
): ViewModel() {

    val data: MutableLiveData<List<ServiceAmountDomainModel>> = MutableLiveData()

    init {
        initialize()
    }

    fun onEvent(event: BarChartEvent) {
        when (event) {
            is BarChartEvent.Init -> initialize()
        }
    }

    private fun initialize() {
        Timber.d("Initialize called...")
        data.value = runBlocking { getServiceAmountDataUseCase().first() }
    }
}

sealed class BarChartEvent {
    data object Init : BarChartEvent()
}