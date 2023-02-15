package com.example.communityapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.communityapplication.R
import com.example.communityapplication.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)

        //binding variables
        val imgBottomHometap = binding.imgBottomHometap
        val imgBottomGoodtip = binding.imgBottomGoodtip
        val imgBottomTalk = binding.imgBottomTalk
        val imgBottomBookmark = binding.imgBottomBookmark
        val imgBottomStore = binding.imgBottomStore

        imgBottomHometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_homeFragment)
        }

        imgBottomGoodtip.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_tipFragment)
        }

        imgBottomTalk.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_talkFragment)
        }

        imgBottomBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_bookmarkFragment)
        }

        imgBottomStore.setOnClickListener {
            //Nothing
        }


        return binding.root
    }

}