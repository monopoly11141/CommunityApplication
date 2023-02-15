package com.example.communityapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.communityapplication.R
import com.example.communityapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
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




        return binding.root
    }

}