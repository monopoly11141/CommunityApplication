package com.example.communityapplication.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.communityapplication.R
import com.example.communityapplication.utils.FirebaseAuthUtil

class LVBoardAdapter(val boardList: MutableList<BoardModel>) : BaseAdapter() {

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

        convertView =
            LayoutInflater.from(parent!!.context).inflate(R.layout.board_list_item, parent, false)

        val llitem: LinearLayout = convertView!!.findViewById(R.id.llitem)
        val tvTitle: TextView = convertView.findViewById(R.id.tvTitle)
        val tvContent: TextView = convertView.findViewById(R.id.tvContent)
        val tvTime: TextView = convertView.findViewById(R.id.tvTime)

        if (boardList[position].uid == FirebaseAuthUtil.getUID()) {
            llitem.setBackgroundColor(Color.parseColor("#FFA500"))
        }

        tvTitle.text = boardList[position].title
        tvContent.text = boardList[position].content
        tvTime.text = boardList[position].time

        return convertView

    }

}