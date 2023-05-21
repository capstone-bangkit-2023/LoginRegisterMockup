package com.example.loginregistermockup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginregistermockup.databinding.ActivityRegister2Binding

class Register2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

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
            } else if (confirmPass.isEmpty()) {
                binding.confirmPassword.error = "Isi password"
            } else if (password != confirmPass) {
                Toast.makeText(this, "Password dan ConfirmPassword tidak sama", Toast.LENGTH_LONG).show()
            } else {
                //Register to server
            }
        }
    }
}