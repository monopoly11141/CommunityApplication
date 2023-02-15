package com.example.communityapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.MainActivity
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityIntroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //binding variables
        val btnLogin = binding.btnLogin
        val btnRegister = binding.btnRegister
        val btnStartWithoutLogin = binding.btnStartWithoutLogin

        btnLogin.setOnClickListener{
            val intentToLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentToLoginActivity)
        }

        btnRegister.setOnClickListener{
            val intentToRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegisterActivity)
        }

        btnStartWithoutLogin.setOnClickListener{
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Toast.makeText(this, "Sign in Anonymously Success", Toast.LENGTH_SHORT).show()

                        val intentToMainActivity = Intent(this, MainActivity::class.java)

                        intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentToMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intentToMainActivity)
                    } else {
                        // Sign in failed
                        Toast.makeText(this, "Sign in Anonymously failed", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}