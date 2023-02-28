package com.example.communityapplication.board

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityBoardEditBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardEditBinding
    private lateinit var key: String
    private lateinit var writerUID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        val btnEdit = binding.btnEdit

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

        btnEdit.setOnClickListener {
            editBoardData(key)
        }

    }

    private fun getBoardData(key: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)


                    var etTitle = binding.etTitle
                    val etContent = binding.etContent

                    etTitle.setText(dataModel?.title)
                    etContent.setText(dataModel?.content)

                    writerUID = dataModel!!.uid
                } catch (e: Exception) {
                    Log.d(TAG, "삭제 완료")
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FirebaseRefUtil.boardRef.child(key).addValueEventListener(postListener)
    }

    private fun getImageData(key: String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child("${key}.jpg")

        // ImageView in your Activity
        val imageView = findViewById<ImageView>(R.id.imgPlus)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->

            if (task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageView)
            }

        })
    }

    private fun editBoardData(key: String) {
        FirebaseRefUtil.boardRef.child(key).setValue(
            BoardModel(
                binding.etTitle.text.toString(),
                binding.etContent.text.toString(),
                writerUID,
                FirebaseAuthUtil.getTime()
            )
        )
        Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show()
        finish()
    }

}