package com.example.communityapplication.board

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityBoardClickedBinding
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardClickedActivity : AppCompatActivity() {

    private val TAG = BoardClickedActivity::class.java.simpleName
    private lateinit var binding: ActivityBoardClickedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_clicked)

        val key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

    }

    private fun getBoardData(key : String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                Log.d(TAG, dataModel!!.title)

                val tvClickedTitle = binding.tvClickedTitle
                val tvClickedTime = binding.tvClickedTime
                val tvClickedContent = binding.tvClickedContent

                tvClickedTitle.text = dataModel!!.title
                tvClickedTime.text = dataModel!!.time
                tvClickedContent.text = dataModel!!.content

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FirebaseRefUtil.boardRef.child(key).addValueEventListener(postListener)
    }

    private fun getImageData(key : String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child("${key}.jpg")

        // ImageView in your Activity
        val imageView = findViewById<ImageView>(R.id.imgClickedImg)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener {task ->

            if(task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageView)
            }

        })
    }

}