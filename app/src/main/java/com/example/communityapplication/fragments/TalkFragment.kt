package com.example.communityapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.communityapplication.R
import com.example.communityapplication.databinding.FragmentTalkBinding

class TalkFragment : Fragment() {

    private lateinit var binding: FragmentTalkBinding
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

}