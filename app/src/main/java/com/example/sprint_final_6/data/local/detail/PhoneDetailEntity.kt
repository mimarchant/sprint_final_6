package com.example.sprint_final_6.data.local.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_details")
data class PhoneDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)