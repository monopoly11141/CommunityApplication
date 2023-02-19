package com.example.communityapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.communityapplication.R
import com.example.communityapplication.contents.RVBookmarkAdapter
import com.example.communityapplication.contents.RVContentModel
import com.example.communityapplication.databinding.FragmentBookmarkBinding
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BookmarkFragment : Fragment() {
    private val TAG = BookmarkFragment::class.java.simpleName
    private lateinit var binding: FragmentBookmarkBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

        //binding variables
        val imgBottomHometap = binding.imgBottomHometap
        val imgBottomGoodtip = binding.imgBottomGoodtip
        val imgBottomTalk = binding.imgBottomTalk
        val imgBottomBookmark = binding.imgBottomBookmark
        val imgBottomStore = binding.imgBottomStore
        val rvBookmark = binding.rvBookmark

        getBookmarkData()

        rvBookmarkAdapter = RVBookmarkAdapter(requireContext(), items, itemKeyList, bookmarkIDList)

        rvBookmark.adapter = rvBookmarkAdapter
        rvBookmark.layoutManager = GridLayoutManager(requireContext(), 2)


        imgBottomHometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_homeFragment)
        }

        imgBottomGoodtip.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_tipFragment)
        }

        imgBottomTalk.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_talkFragment)
        }

        imgBottomBookmark.setOnClickListener {
            //Nothing
        }

        imgBottomStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_storeFragment)
        }

        return binding.root

    }

    private fun getCategoryData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(RVContentModel::class.java)
                    if(bookmarkIDList.contains(dataModel.key.toString())) {
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }

                    //Log.d(this::class.java.toString(), dataModel.toString())
                }
                rvBookmarkAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        FirebaseRefUtil.category1.addValueEventListener(postListener)
        FirebaseRefUtil.category2.addValueEventListener(postListener)
    }

    private fun getBookmarkData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children) {
                    bookmarkIDList.add(dataModel.key.toString())

                }
                getCategoryData()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseRefUtil.bookmarkRef.child(FirebaseAuthUtil.getUID()).addValueEventListener(postListener)
    }
}