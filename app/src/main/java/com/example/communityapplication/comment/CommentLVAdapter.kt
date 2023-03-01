package com.example.communityapplication.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.communityapplication.R

class CommentLVAdapter(val commentList: MutableList<CommentModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return commentList.size
    }

    override fun getItem(position: Int): Any {
        return commentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (convertView == null) {
            convertView =
                LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.comment_list_item, parent, false)
        }

        val tvTitle: TextView = convertView!!.findViewById(R.id.tvTitle)
        val tvTime: TextView = convertView.findViewById(R.id.tvTime)


        tvTitle.text = commentList[position].commentTitle
        tvTime.text = commentList[position].time

        return convertView!!
    }


}