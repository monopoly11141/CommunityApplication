package com.example.communityapplication.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.MainActivity
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //binding variables
        val etEmail = binding.etEmail
        val etPassword = binding.etPassword
        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()

                        val intentToMainActivity = Intent(this, MainActivity::class.java)

                        intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intentToMainActivity)
                    } else {
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}