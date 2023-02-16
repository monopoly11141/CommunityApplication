package com.example.communityapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.communityapplication.R
import com.example.communityapplication.contents.ContentsActivity
import com.example.communityapplication.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        //binding variables
        val imgBottomHometap = binding.imgBottomHometap
        val imgBottomGoodtip = binding.imgBottomGoodtip
        val imgBottomTalk = binding.imgBottomTalk
        val imgBottomBookmark = binding.imgBottomBookmark
        val imgBottomStore = binding.imgBottomStore
        val imgAll = binding.imgAll
        val imgCook = binding.imgCook
        val imgEconomy = binding.imgEconomy
        val imgElse = binding.imgElse
        val imgHobby = binding.imgHobby
        val imgInterior = binding.imgInterior
        val imgLife = binding.imgLife
        val imgOneRoom = binding.imgOneRoom


        imgBottomHometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }

        imgBottomGoodtip.setOnClickListener {
            //Nothing
        }

        imgBottomTalk.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)
        }

        imgBottomBookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_bookmarkFragment)
        }

        imgBottomStore.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)
        }

        imgAll.setOnClickListener{
            val intentToContentsActivity = Intent(context, ContentsActivity::class.java)
            startActivity(intentToContentsActivity)
        }

        return binding.root
    }

}