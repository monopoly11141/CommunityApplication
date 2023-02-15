package com.example.communityapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.MainActivity
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //binding variables
        val etEmail = binding.etEmail
        val etPassword = binding.etPassword
        val etPasswordRetype = binding.etPasswordRetype
        val btnRegister = binding.btnRegister


        btnRegister.setOnClickListener {
            if(etEmail.text.isEmpty() || etPassword.text.isEmpty() || etPasswordRetype.text.toString() != etPassword.text.toString()) {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }else {
                auth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                            val intentToMainActivity = Intent(this, MainActivity::class.java)
                            intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentToMainActivity)
                        } else {
                            Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }


    }
}