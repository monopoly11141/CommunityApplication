package com.example.communityapplication.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.R
import com.example.communityapplication.auth.IntroActivity
import com.example.communityapplication.databinding.ActivitySettingBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        val btnLogout = binding.btnLogout

        btnLogout.setOnClickListener {

            Firebase.auth.signOut()

            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()

            val intentToIntroActivity = Intent(this, IntroActivity::class.java)
            intentToIntroActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intentToIntroActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intentToIntroActivity)
        }

    }
}