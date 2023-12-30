package com.dennnytech.paydash.barchart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennytech.domain.models.ServiceAmountDomainModel
import com.dennytech.domain.usecases.transactions.GetServiceAmountDataUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BarchartViewModel @Inject constructor(
    private val getServiceAmountDataUseCase: GetServiceAmountDataUseCase
): ViewModel() {

    val data: MutableLiveData<List<ServiceAmountDomainModel>> = MutableLiveData()

    fun onEvent(event: BarChartEvent) {
        when (event) {
            is BarChartEvent.Init -> init()
        }
    }

    private fun init() {
        viewModelScope.launch {
            getServiceAmountDataUseCase().collect {
                data.value = it
            }
        }
    }
}

sealed class BarChartEvent {
    data object Init : BarChartEvent()
}