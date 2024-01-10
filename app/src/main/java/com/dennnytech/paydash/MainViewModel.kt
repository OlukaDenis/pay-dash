package com.dennnytech.paydash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennytech.domain.models.Resource
import com.dennytech.domain.usecases.sync.SyncUseCase
import com.dennytech.domain.usecases.transactions.GetTransactionCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionCountUseCase: GetTransactionCountUseCase,
    private val syncUseCase: SyncUseCase
) : ViewModel() {

    val remoteLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Init -> init()
        }
    }

    private fun init() {
        val tranCount = runBlocking { transactionCountUseCase() }

        viewModelScope.launch {
            if (tranCount == 0) {
                syncUseCase().collect {
                    when (it) {
                        is Resource.Loading -> remoteLoading.value = true
                        is Resource.Error -> {
                            remoteLoading.value = false
                            Timber.e("Error: %s", it.exception)
                        }

                        is Resource.Success -> {
                            remoteLoading.value = false
                            Timber.d("Success")
                        }
                    }
                }
            } else {
                remoteLoading.value = false
            }
        }
    }
}

sealed class HomeEvent {
    data object Init : HomeEvent()
}
