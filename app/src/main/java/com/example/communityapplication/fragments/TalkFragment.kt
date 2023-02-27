package com.example.communityapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.communityapplication.R
import com.example.communityapplication.board.BoardClickedActivity
import com.example.communityapplication.board.BoardModel
import com.example.communityapplication.board.BoardWriteActivity
import com.example.communityapplication.board.LVBoardAdapter
import com.example.communityapplication.databinding.FragmentTalkBinding
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TalkFragment : Fragment() {

    private val TAG = TalkFragment::class.java.simpleName
    private lateinit var binding: FragmentTalkBinding
    val boardDataList = mutableListOf<BoardModel>()
    private lateinit var adapter : LVBoardAdapter
    private val boardKeyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        //binding variables
        val imgBottomHometap = binding.imgBottomHometap
        val imgBottomGoodtip = binding.imgBottomGoodtip
        val imgBottomTalk = binding.imgBottomTalk
        val imgBottomBookmark = binding.imgBottomBookmark
        val imgBottomStore = binding.imgBottomStore
        val imgWrite = binding.imgWrite
        val lvBoard = binding.lvBoard

        getFirebaseBoardData()

        adapter = LVBoardAdapter(boardDataList)
        lvBoard.adapter = adapter

        lvBoard.setOnItemClickListener{parent, view, position, id ->
//            val intentToBoardClickedActivity = Intent(context, BoardClickedActivity::class.java)
//
//            intentToBoardClickedActivity.putExtra("title", boardDataList[position].title)
//            intentToBoardClickedActivity.putExtra("content", boardDataList[position].content)
//            intentToBoardClickedActivity.putExtra("time", boardDataList[position].time)
//
//            startActivity(intentToBoardClickedActivity)
            val intentToBoardClickedActivity = Intent(context, BoardClickedActivity::class.java)
            intentToBoardClickedActivity.putExtra("key", boardKeyList[position])
            startActivity(intentToBoardClickedActivity)
        }

        imgWrite.setOnClickListener {
            val intentToBoardWriteActivity = Intent(context, BoardWriteActivity::class.java)
            startActivity(intentToBoardWriteActivity)
        }

        imgBottomHometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        imgBottomGoodtip.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        imgBottomTalk.setOnClickListener {
            //Nothing
        }

        imgBottomBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }

        imgBottomStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        return binding.root
    }

    private fun getFirebaseBoardData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)

                    boardKeyList.add(dataModel.key.toString())
                }

                boardKeyList.reverse()
                boardDataList.reverse()

                adapter.notifyDataSetChanged()
                Log.d(TAG, boardDataList.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }
        FirebaseRefUtil.boardRef.addValueEventListener(postListener)

    }

}