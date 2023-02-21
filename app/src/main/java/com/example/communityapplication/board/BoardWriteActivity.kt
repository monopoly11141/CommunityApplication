package com.example.communityapplication.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.R
import com.example.communityapplication.contents.BookmarkModel
import com.example.communityapplication.databinding.ActivityBoardWriteBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        //binding variables
        val btnWrite = binding.btnWrite
        val etContent = binding.etContent
        val etTitle = binding.etTitle
        val imgPlus = binding.imgPlus

        btnWrite.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()
            val uid = FirebaseAuthUtil.getUID()
            val time = FirebaseAuthUtil.getTime()

            FirebaseRefUtil.boardRef.push().setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_SHORT).show()

            finish()
        }


    }
}