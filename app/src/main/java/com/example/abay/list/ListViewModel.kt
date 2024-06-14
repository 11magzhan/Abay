package com.example.abay.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abay.database.QaraSoz
import com.example.abay.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val qaraSozList: LiveData<List<QaraSoz>> = repository.getAllQaraSoz()

    init {
        viewModelScope.launch {
            repository.refreshQaraSozder()
        }
    }
}