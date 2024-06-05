package com.example.abay.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abay.database.QaraSoz
import com.example.abay.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _randomQaraSoz = MutableLiveData<QaraSoz>()
    val randomQaraSoz: LiveData<QaraSoz> = _randomQaraSoz

    private val _currentQaraSoz = MutableLiveData<QaraSoz?>()
    val currentQaraSoz: LiveData<QaraSoz?> = _currentQaraSoz

    fun getQaraSozById(id: Long?) {
        viewModelScope.launch {
            val qaraSoz = id?.let { repository.getQaraSozById(it) }
            _currentQaraSoz.value = qaraSoz
        }
    }

     fun getRandomQaraSoz() {
        viewModelScope.launch {
            val qaraSoz = repository.getRandomQaraSoz()
            _randomQaraSoz.value = qaraSoz
            _currentQaraSoz.value = qaraSoz
        }
    }

    fun changeFavorite() {
        _currentQaraSoz.value?.let {
            val newFavValue = if (it.favorite == 1) 0 else 1
            val updatedQaraSoz = it.copy(favorite = newFavValue)
            viewModelScope.launch {
                repository.updateQaraSoz(updatedQaraSoz)
                _currentQaraSoz.value = updatedQaraSoz
            }
        }
    }
}