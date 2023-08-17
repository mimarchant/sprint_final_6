package com.example.sprint_final_6.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sprint_final_6.data.local.PhoneDao
import com.example.sprint_final_6.data.local.detail.PhoneDetailEntity
import com.example.sprint_final_6.data.local.list.PhoneListEntity
import com.example.sprint_final_6.data.remote.PhoneApi

class Repository(private val phoneApi: PhoneApi, private val phoneDao: PhoneDao) {
    fun getPhonesFromEntity() : LiveData<List<PhoneListEntity>> = phoneDao.getCellPhones()

    fun getPhoneDetailsFromEntity(id: Int): LiveData<PhoneDetailEntity> = phoneDao.getCellPhoneDetails(id)

    suspend fun getPhones() {
        try {
            val response = phoneApi.getDataPhones()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val cellPhoneEntity = it.map {it.transformToEntity()}
                    phoneDao.insertCellPhones(cellPhoneEntity)
                }
            }
        } catch (exc: Exception) {
            Log.e("catch err", "")
        }
    }

    suspend fun getPhoneDetails(id: Int) {
        try {
            val response = phoneApi.getDataPhoneDetails(id)
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val phoneDetailEntity = it.transformToEntity()
                    phoneDao.insertCellPhoneDetails(phoneDetailEntity)
                }
            }
        } catch (exp: Exception) {
            Log.e("catch err", "")
        }
    }


}