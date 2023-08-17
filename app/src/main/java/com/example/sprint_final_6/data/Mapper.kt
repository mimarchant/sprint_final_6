package com.example.sprint_final_6.data


import com.example.sprint_final_6.data.local.detail.PhoneDetailEntity
import com.example.sprint_final_6.data.local.list.PhoneListEntity
import com.example.sprint_final_6.data.remote.detail.CellPhoneDetail
import com.example.sprint_final_6.data.remote.list.CellPhoneList

fun CellPhoneList.transformToEntity(): PhoneListEntity = PhoneListEntity(this.id, this.name, this.price, this.image)

fun CellPhoneDetail.transformToEntity(): PhoneDetailEntity = PhoneDetailEntity(
    this.id,
    this.name,
    this.price,
    this.image,
    this.description,
    this.lastPrice,
    this.credit
)