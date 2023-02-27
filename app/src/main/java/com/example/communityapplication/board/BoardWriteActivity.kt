package com.example.communityapplication.board

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.communityapplication.R
import com.example.communityapplication.contents.BookmarkModel
import com.example.communityapplication.databinding.ActivityBoardWriteBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding
    private var isImageUpload = false

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

            val key = FirebaseRefUtil.boardRef.push().key.toString()

            FirebaseRefUtil.boardRef.child(key).setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_SHORT).show()
            if(isImageUpload) {
                imageUpload(key)
            }

            finish()
        }

        imgPlus.setOnClickListener{
            isImageUpload = true

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startForResult.launch(gallery)
        }


    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            binding.imgPlus.setImageURI(intent?.data)
            // Handle the Intent
            //do stuff here
        }
    }

    private fun imageUpload(key : String) {
        val imageView = binding.imgPlus

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("${key}.jpg")

        // Get the data from an ImageView as bytes
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Log.d(TAG, "Uplaod Fail")
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
}