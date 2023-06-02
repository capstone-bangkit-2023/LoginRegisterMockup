package com.example.loginregistermockup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.loginregistermockup.databinding.ActivityLoginBinding
import com.example.loginregistermockup.token.TokenPreference
import com.example.loginregistermockup.token.TokenViewModel
import com.example.loginregistermockup.token.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = TokenPreference.getInstance(dataStore)
        val tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        tokenViewModel.getToken().observe(this) {
            if (it != "") {
                viewModel.loginMsg.observe(this) { msg ->
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
                }
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            }
        }

        binding.registerBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (password.length < 8) {
                binding.username.error = "Min 8 char"
            } else if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill detail", Toast.LENGTH_LONG).show()
            } else if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.postLogin(username, password)
            }

            viewModel.loginResult.observe(this) {
                tokenViewModel.saveToken(it.accessToken)
            }

        }
    }
}