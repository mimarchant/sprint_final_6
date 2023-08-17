package com.example.sprint_final_6.data.local.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_list")
class PhoneListEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String
)