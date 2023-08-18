package com.example.sprint_final_6.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint_final_6.data.PhoneDatabase
import com.example.sprint_final_6.data.Repository
import com.example.sprint_final_6.data.local.PhoneDao
import com.example.sprint_final_6.data.remote.PhoneRetrofit
import kotlinx.coroutines.launch

class PhoneViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository

    fun phonesLiveData() = repository.getPhonesFromEntity()

    fun phoneDetailsData(id: Int) = repository.getPhoneDetailsFromEntity(id)

    init {
        val api = PhoneRetrofit.getCellPhoneRetrofit()
        val phoneDatabase: PhoneDao = PhoneDatabase.getDataBase(application)
            .getCellPhonesDAO()
        repository = Repository(api, phoneDatabase)
    }

    fun getPhonesViewModel() = viewModelScope.launch { repository.getPhones() }
    fun getPhoneDetails(id: Int) = viewModelScope.launch {repository.getPhoneDetails(id)}
}