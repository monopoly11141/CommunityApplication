package com.example.communityapplication.contents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityContentsBinding

class ContentsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityContentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contents)

        val rvContents = binding.rvContents

        val contents = mutableListOf<RVContentModel>()
        
        contents.add(RVContentModel("밥솥 리코타치즈",
            "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
        "https://philosopher-chan.tistory.com/1235"))
        contents.add(RVContentModel("황금노른자장",
            "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
           "https://philosopher-chan.tistory.com/1236"))
        contents.add(RVContentModel("사골곰탕 파스타",
            "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
            "https://philosopher-chan.tistory.com/1237"
            ))


        val rvContentAdapter = RVContentsAdapter(baseContext, contents)
        rvContents.adapter = rvContentAdapter

        rvContents.layoutManager = GridLayoutManager(this, 2)

        rvContentAdapter.itemClick = object : RVContentsAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, contents[position].name, Toast.LENGTH_SHORT).show()

                val intentToContentWebViewActivity = Intent(this@ContentsActivity, ContentWebActivity::class.java)
                intentToContentWebViewActivity.putExtra("websiteURL", contents[position].websiteURL)
                startActivity(intentToContentWebViewActivity)
            }

        }

    }
}