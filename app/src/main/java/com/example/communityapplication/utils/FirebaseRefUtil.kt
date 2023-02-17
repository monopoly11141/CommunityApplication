package com.example.communityapplication.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRefUtil {
    companion object {
        val database = Firebase.database

        val bookmarkRef = database.getReference("bookmark_list")
    }
}