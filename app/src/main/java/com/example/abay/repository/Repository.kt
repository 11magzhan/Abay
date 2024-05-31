package com.example.abay.repository

import com.example.abay.database.QaraSoz
import com.example.abay.database.QaraSozDao
import com.example.abay.mapQaraSozEntityToQaraSoz
import com.example.abay.network.ApiService

class Repository(private val apiService: ApiService, private val qaraSozDao: QaraSozDao) {

    suspend fun refreshQaraSozder() {
        try {
            val response = apiService.getAllQaraSoz(emptyMap())
            val qaraSozModels = response.data.map { mapQaraSozEntityToQaraSoz(it) }
            qaraSozDao.insertAll(qaraSozModels)
        } catch (e: Exception) {
            e.printStackTrace()
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
    }
}