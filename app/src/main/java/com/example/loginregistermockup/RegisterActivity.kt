package com.example.loginregistermockup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginregistermockup.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            val fullName = binding.fullname.text.toString()
            val school = binding.school.text.toString()
            val email = binding.email.text.toString()

            if (fullName.isEmpty() && email.isEmpty()) {
                binding.fullname.error = "Isi nama"
                binding.email.error = "Isi Email"
            } else if (email.isEmpty()) {
                binding.email.error = "Isi Email"
            } else if (fullName.isEmpty()) {
                binding.fullname.error = "Isi nama"
            } else {
                val intent = Intent(this, Register2Activity::class.java)
                intent.putExtra("fullname", fullName)
                intent.putExtra("school", school)
                intent.putExtra("email", email)
                startActivity(intent)
            }
        }
    }
}