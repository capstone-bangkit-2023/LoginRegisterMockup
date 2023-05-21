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
            val phone = binding.phone.text.toString()

            if (fullName.isEmpty() && phone.isEmpty()) {
                binding.fullname.error = "Isi nama"
                binding.phone.error = "Isi No HP"
            } else if (phone.isEmpty()) {
                binding.phone.error = "Isi No HP"
            } else if (fullName.isEmpty()) {
                binding.fullname.error = "Isi nama"
            } else {
                val intent = Intent(this, Register2Activity::class.java)
                /* Implement later
                intent.putExtra("fullname", fullName)
                intent.putExtra("school", school)
                intent.putExtra("phone", phone)*/
                startActivity(intent)
            }
        }
    }
}