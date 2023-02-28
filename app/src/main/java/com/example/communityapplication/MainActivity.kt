package com.example.communityapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.auth.IntroActivity
import com.example.communityapplication.databinding.ActivityMainBinding
import com.example.communityapplication.setting.SettingActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val imgMainMenu = binding.imgMainMenu

        imgMainMenu.setOnClickListener {
            val intentToSettingActivity = Intent(this, SettingActivity::class.java)
            startActivity(intentToSettingActivity)
        }

        //binding variable
//        val btnLogout = binding.btnLogout
//
//        btnLogout.setOnClickListener {
//            Firebase.auth.signOut()
//            Toast.makeText(this, "Logout Completed", Toast.LENGTH_SHORT).show()
//
//            val intentToIntroActivity = Intent(this, IntroActivity::class.java)
//
//            intentToIntroActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intentToIntroActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            startActivity(intentToIntroActivity)
//
//        }

    }
}