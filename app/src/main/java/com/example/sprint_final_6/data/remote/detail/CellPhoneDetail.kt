package com.example.sprint_final_6.data.remote.detail

data class CellPhoneDetail(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)