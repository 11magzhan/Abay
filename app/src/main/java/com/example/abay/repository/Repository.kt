package com.example.abay.repository

import androidx.lifecycle.LiveData
import com.example.abay.database.QaraSoz
import com.example.abay.database.QaraSozDao
import com.example.abay.mapQaraSozEntityToQaraSoz
import com.example.abay.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val qaraSozDao: QaraSozDao) {

    suspend fun refreshQaraSozder() {
        if (qaraSozDao.getCount() == 0) {   // add isUpdated in api todo
            try {
                val response = apiService.getAllQaraSoz(emptyMap())
                val qaraSozModels = response.data.map { mapQaraSozEntityToQaraSoz(it) }
                qaraSozDao.insertAll(qaraSozModels)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getQaraSozByNumber(number: Int): QaraSoz {
        val qaraSozResponse = apiService.getQaraSozByNumber(number)
        val qaraSozEntity = qaraSozResponse.data
        return mapQaraSozEntityToQaraSoz(qaraSozEntity)
    }

    suspend fun getRandomQaraSoz(): QaraSoz {
        val randomNumber = (1..5).random()
        return getQaraSozByNumber(randomNumber)
    } // todo utils or random  get size by list size or -1 if empty

    suspend fun updateQaraSoz(qaraSoz: QaraSoz) {
        qaraSozDao.update(qaraSoz)
    }

    suspend fun getQaraSozById(id: Long): QaraSoz? {
        return qaraSozDao.get(id)
    }

    fun getAllQaraSoz(): LiveData<List<QaraSoz>> {
        return qaraSozDao.getAll()
    }

    fun getAllFavorites(): LiveData<List<QaraSoz>> {
        return qaraSozDao.getAllFavorites()
    }
}