package com.example.sprint_final_6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sprint_final_6.data.local.detail.PhoneDetailEntity
import com.example.sprint_final_6.data.local.list.PhoneListEntity


@Dao
interface PhoneDao {
    /*esto es para la lista*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCellPhones(cellPhoneEntity: List<PhoneListEntity>)

    @Query("Select * from phone_list order by id ASC")
    fun getCellPhones(): LiveData<List<PhoneListEntity>>

    /*esto es para los detalles del telefono*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCellPhoneDetails(cellPhoneDetailEntity: PhoneDetailEntity)

    @Query("Select * from phone_details WHERE id = :id")
    fun getCellPhoneDetails(id: Int): LiveData<PhoneDetailEntity>
}