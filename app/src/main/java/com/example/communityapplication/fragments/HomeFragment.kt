package com.example.communityapplication.fragments

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.communityapplication.R
import com.example.communityapplication.contents.RVBookmarkAdapter
import com.example.communityapplication.contents.RVContentModel
import com.example.communityapplication.databinding.FragmentHomeBinding
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val contentsArrayList = ArrayList<RVContentModel>()
    private lateinit var rvBookmarkAdapter : RVBookmarkAdapter
    private var bookmarkIDList = mutableListOf<String>()
    private var items = ArrayList<RVContentModel>()
    private var itemKeyList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //binding variables
        val imgBottomHometap = binding.imgBottomHometap
        val imgBottomGoodtip = binding.imgBottomGoodtip
        val imgBottomTalk = binding.imgBottomTalk
        val imgBottomBookmark = binding.imgBottomBookmark
        val imgBottomStore = binding.imgBottomStore
        val rvBookmark = binding.rvMain

        imgBottomHometap.setOnClickListener {
           //Nothing
        }

        imgBottomGoodtip.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }

        imgBottomTalk.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }

        imgBottomBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }

        imgBottomStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }


        getCategoryData()

        rvBookmarkAdapter = RVBookmarkAdapter(requireContext(), items, itemKeyList, bookmarkIDList)

        rvBookmark.adapter = rvBookmarkAdapter
        rvBookmark.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    private fun getCategoryData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(RVContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())

                }

                rvBookmarkAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FirebaseRefUtil.category1.addValueEventListener(postListener)
        FirebaseRefUtil.category2.addValueEventListener(postListener)

    }

}