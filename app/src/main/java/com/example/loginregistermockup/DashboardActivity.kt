package com.example.loginregistermockup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.loginregistermockup.databinding.ActivityDashboardBinding
import com.example.loginregistermockup.token.TokenPreference
import com.example.loginregistermockup.token.TokenViewModel
import com.example.loginregistermockup.token.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = TokenPreference.getInstance(dataStore)
        val tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        binding.logoutBtn.setOnClickListener {
            tokenViewModel.saveToken("")
            Thread.sleep(500)// Wait for token to be deleted
            finish()
        }
    }
}