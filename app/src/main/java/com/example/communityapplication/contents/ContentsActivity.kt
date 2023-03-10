package com.example.communityapplication.contents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityContentsBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentsBinding
    private val RVContentModelRicotaCheese = RVContentModel("밥솥 리코타치즈",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png",
        "https://philosopher-chan.tistory.com/1235")

    private val RVContentModelEgg = RVContentModel("황금노른자장",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png",
        "https://philosopher-chan.tistory.com/1236")

    private val RVContentModelPasta = RVContentModel("사골곰탕 파스타",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png",
        "https://philosopher-chan.tistory.com/1237")

    val bookmarkIDList = mutableListOf<String>()
    lateinit var rvContentAdapter : RVContentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contents)

        val contents = mutableListOf<RVContentModel>()
        val contentsKeyList = mutableListOf<String>()

        val rvContents = binding.rvContents
        rvContentAdapter = RVContentsAdapter(baseContext, contents, contentsKeyList, bookmarkIDList)

        // Write a message to the database
        val database = Firebase.database
        val category = intent.getStringExtra("category")

        lateinit var myRef : DatabaseReference

        when (category) {
            "category1" -> {myRef = database.getReference("contents")}
            "category2" -> {myRef = database.getReference("contents2")}
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {

                    val content = dataModel.getValue(RVContentModel::class.java)
                    contents.add(content!!)

                    contentsKeyList.add(dataModel.key.toString())

                }
                rvContentAdapter.notifyDataSetChanged()
                Log.d("ContentsActivity", contents.toString())

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(postListener)

        rvContents.adapter = rvContentAdapter

        rvContents.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()

    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                bookmarkIDList.clear()

                for(dataModel in dataSnapshot.children) {
                    bookmarkIDList.add(dataModel.key.toString())

                }
                Log.d(this::class.java.toString(), bookmarkIDList.toString())
                rvContentAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRefUtil.bookmarkRef.child(FirebaseAuthUtil.getUID()).addValueEventListener(postListener)
    }



}