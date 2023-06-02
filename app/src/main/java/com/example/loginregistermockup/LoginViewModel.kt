package com.example.loginregistermockup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginregistermockup.api.ApiConfig
import com.example.loginregistermockup.api.Data
import com.example.loginregistermockup.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginMsg = MutableLiveData<String>()
    val loginMsg: LiveData<String> = _loginMsg

    private val _loginResult = MutableLiveData<Data>()
    val loginResult: LiveData<Data> = _loginResult
    fun postLogin(username: String, password: String) {
        val api = ApiConfig.getApiService().loginApi(username, password)
        api.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        _loginMsg.value = response.body()!!.status
                        _loginResult.value = response.body()!!.data
                    }
                } else {
                    Log.e("postLogin", "isFailed")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("postLogin", "onFailure : ${t.message}")
            }

        })
    }
}