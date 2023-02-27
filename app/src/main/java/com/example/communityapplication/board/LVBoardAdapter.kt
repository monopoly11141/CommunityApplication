package com.example.communityapplication.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.communityapplication.R

class LVBoardAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        if(convertView == null) {
            convertView = LayoutInflater.from(parent!!.context).inflate(R.layout.board_list_item, parent,false)
        }

        val tvTitle : TextView = convertView!!.findViewById(R.id.tvTitle)
        val tvContent : TextView = convertView!!.findViewById(R.id.tvContent)
        val tvTime : TextView = convertView!!.findViewById(R.id.tvTime)

        tvTitle.text = boardList[position].title
        tvContent.text = boardList[position].content
        tvTime.text = boardList[position].time

        return convertView!!

    }

}