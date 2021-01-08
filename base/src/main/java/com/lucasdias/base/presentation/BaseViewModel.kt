package com.lucasdias.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdias.core.resource.Resource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : Any?>(
    private val coroutineContext: CoroutineDispatcher
) : ViewModel() {

    val responseLiveData: LiveData<Resource<T>> by lazy { _responseMutableLiveData }
    private var hasNetworkConnectivity = true
    private val _responseMutableLiveData: MutableLiveData<Resource<T>> by lazy { MutableLiveData<Resource<T>>() }
    private var isInitialRequest = true
    private var isLoading = false

    abstract suspend fun request(): Resource<T>

    fun executeRequest() {
        if (hasNetworkConnectivity.not()) return
        setLoadingStatus()

        launch {
            val response = request()
            requestHandler(response)
        }
    }

    fun ViewModel.launch(
        context: CoroutineContext = coroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, block = block)

    fun isLoading() = isLoading

    fun isNotLoading() = isLoading.not()

    fun isInitialRequest() = isInitialRequest

    fun isNotInitialRequest() = isInitialRequest.not()

    private fun setLoadingStatus() {
        requestHandler(Resource.Loading())
    }

    private fun requestHandler(resource: Resource<T>) {
        when (resource) {
            is Resource.Success -> {
                isLoading = false
                isInitialRequest = false
                _responseMutableLiveData.postValue(resource)
            }
            is Resource.Error -> {
                isLoading = false
                _responseMutableLiveData.postValue(resource)
            }
            is Resource.Loading -> {
                isLoading = true
                _responseMutableLiveData.postValue(Resource.Loading())
            }
        }
    }

    fun updateConnectivityStatus(hasNetworkConnectivity: Boolean) {
        this.hasNetworkConnectivity = hasNetworkConnectivity
    }
}
