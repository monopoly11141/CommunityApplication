package com.example.communityapplication.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtil {

    companion object {
        private lateinit var auth : FirebaseAuth

        fun getUID() : String {
            auth = FirebaseAuth.getInstance()
            return auth.currentUser?.uid.toString()

        }
    }
}