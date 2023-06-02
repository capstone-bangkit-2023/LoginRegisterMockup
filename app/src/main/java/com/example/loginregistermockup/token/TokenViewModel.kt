package com.example.loginregistermockup.token

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TokenViewModel(private val pref: TokenPreference) : ViewModel() {

    fun getToken() : LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

}