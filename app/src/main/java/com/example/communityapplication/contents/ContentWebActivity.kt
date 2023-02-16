package com.example.communityapplication.contents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.example.communityapplication.R

class ContentWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_web)

        val websiteURL = intent.getStringExtra("websiteURL")

        val wbContent : WebView = findViewById(R.id.wbContent)
        wbContent.loadUrl(websiteURL.toString())


    }
}