package com.example.loginregistermockup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.loginregistermockup.databinding.ActivityRegister2Binding

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val fullname = intent.getStringExtra("fullname")
        val school = intent.getStringExtra("school")
        val email = intent.getStringExtra("email")

        binding.registerBtnApi.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()

            if (username.isEmpty() && password.isEmpty() && confirmPass.isEmpty()) {
                binding.username.error = "Isi username"
                binding.password.error = "Isi password"
                binding.confirmPassword.error = "Isi password"
            } else if (username.isEmpty()) {
                binding.username.error = "Isi username"
            } else if (password.isEmpty()) {
                binding.password.error = "Isi password"
            } else if (password.length < 8) {
                binding.password.error = "Password kurang dari 8 karakter"
            } else if (confirmPass.isEmpty()) {
                binding.confirmPassword.error = "Isi password"
            } else if (password != confirmPass) {
                Toast.makeText(this, "Password dan ConfirmPassword tidak sama", Toast.LENGTH_LONG).show()
            } else {
                viewModel.postRegister(username, password, confirmPass, fullname!!, school, email!!)
                viewModel.registerMsg.observe(this) {
                    Toast.makeText(this@Register2Activity, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}