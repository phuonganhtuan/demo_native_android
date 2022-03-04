package com.example.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.LoadDataState
import com.example.data.models.ActivityEntity
import com.example.data.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ActivityRepository) : ViewModel() {

    val activityList: StateFlow<List<ActivityEntity>> get() = _activityList
    private val _activityList =
        MutableStateFlow(
            emptyList<ActivityEntity>()
        )

    val loadDataState: StateFlow<LoadDataState> get() = _loadDataState
    private val _loadDataState = MutableStateFlow(LoadDataState.NONE)

    fun getActivity() = viewModelScope.launch {
        val resultList = mutableListOf<ActivityEntity>()
        withContext(Dispatchers.IO) {
            try {
                _loadDataState.value = LoadDataState.LOADING
                val flows = mutableListOf<Job>()
                for (i in 0 until 100) {
                    flows.add(launch {
                        resultList.add(repository.getActivity())
                    })
                }
                flows.joinAll()
                _activityList.value = resultList
                _loadDataState.value = LoadDataState.SUCCESS
            } catch (exception: Exception) {
                _loadDataState.value = LoadDataState.ERROR
                return@withContext
            }
        }
    }
}
