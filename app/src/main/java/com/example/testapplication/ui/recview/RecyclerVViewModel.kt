package com.example.testapplication.ui.recview

import androidx.lifecycle.*
import com.example.testapplication.data.RetrofitRequestRepository
import com.example.testapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import javax.inject.Inject
import javax.inject.Provider

class RecyclerVViewModel(private val retrofitRepository: RetrofitRequestRepository) : ViewModel() {

    fun getResponse() = flow {
        try {
            emit(Resource.success(data = retrofitRepository.getArticles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Resource loading!"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Resource.loading(data = null)
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        class RecyclerVViewModelFactory @Inject constructor(
            private val retrofitRepository: Provider<RetrofitRequestRepository>
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == RecyclerVViewModel::class.java)
                return RecyclerVViewModel(retrofitRepository.get()) as T
            }
        }
    }
}

