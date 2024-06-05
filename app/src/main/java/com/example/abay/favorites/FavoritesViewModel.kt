package com.example.abay.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.abay.database.QaraSoz
import com.example.abay.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val favoriteQaraSozList: LiveData<List<QaraSoz>> = repository.getAllFavorites()
}