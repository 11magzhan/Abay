package com.example.abay.today

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abay.database.QaraSoz
import com.example.abay.database.QaraSozDatabase
import com.example.abay.network.Api
import com.example.abay.repository.Repository
import kotlinx.coroutines.launch

class TodayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val _randomQaraSoz = MutableLiveData<QaraSoz>()
    val randomQaraSoz: LiveData<QaraSoz> get() = _randomQaraSoz

    init {
        val qaraSozDao = QaraSozDatabase.getDatabase(application).qaraSozDao()
        val apiService = Api.retrofitService
        repository = Repository(apiService, qaraSozDao)
        fetchRandomQaraSoz()
    }

    private fun fetchRandomQaraSoz() {
        viewModelScope.launch {
            val qaraSoz = repository.getRandomQaraSoz()
            _randomQaraSoz.postValue(qaraSoz)
        }
    }
}