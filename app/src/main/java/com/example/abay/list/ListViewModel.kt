package com.example.abay.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abay.database.QaraSoz
import com.example.abay.database.QaraSozDatabase
import com.example.abay.network.Api
import com.example.abay.repository.Repository
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val qaraSozList: LiveData<List<QaraSoz>>

    init {
        val qaraSozDao = QaraSozDatabase.getDatabase(application).qaraSozDao()
        val apiService = Api.retrofitService
        repository = Repository(apiService, qaraSozDao)
        qaraSozList = qaraSozDao.getAll()

        viewModelScope.launch {
            repository.refreshQaraSozder()
        }
    }
}