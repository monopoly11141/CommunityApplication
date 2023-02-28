package com.example.communityapplication.board

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.communityapplication.R
import com.example.communityapplication.databinding.ActivityBoardClickedBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
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
    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_clicked)

        val imgMainMenu = binding.imgMainMenu

        imgMainMenu.setOnClickListener {
            showMainMenuDialog()
        }

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)

    }

    private fun getBoardData(key : String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try{
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)

                    val tvClickedTitle = binding.tvClickedTitle
                    val tvClickedTime = binding.tvClickedTime
                    val tvClickedContent = binding.tvClickedContent

                    tvClickedTitle.text = dataModel!!.title
                    tvClickedTime.text = dataModel!!.time
                    tvClickedContent.text = dataModel!!.content

                    if(dataModel!!.uid == FirebaseAuthUtil.getUID()) {
                        binding.imgMainMenu.isVisible = true
                        Toast.makeText(baseContext, "내가 글쓴이", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(baseContext, "내가 글쓴이가 아님", Toast.LENGTH_SHORT).show()
                    }

                }catch(e : Exception) {
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

    private fun showMainMenuDialog() {

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_main_menu, null)

        val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setTitle("게시글 수정 삭제")

        val alertDialog = mBuilder.show()

        val btnChange = alertDialog.findViewById<Button>(R.id.btnChange)

        val btnDelete = alertDialog.findViewById<Button>(R.id.btnDelete)

        btnChange?.setOnClickListener{
            Toast.makeText(this, "수정 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show()

            val intentToBoardEditActivity = Intent(this, BoardEditActivity::class.java)
            intentToBoardEditActivity.putExtra("key", key)
            startActivity(intentToBoardEditActivity)

        }

        btnDelete?.setOnClickListener{
            FirebaseRefUtil.boardRef.child(key).removeValue()
            Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

}