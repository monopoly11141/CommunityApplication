package com.example.communityapplication.contents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.communityapplication.R

class RVContentsAdapter(val context : Context, val contents : MutableList<RVContentModel>) :
    RecyclerView.Adapter<RVContentsAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVContentsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_contents_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVContentsAdapter.ViewHolder, position: Int) {
        if(itemClick != null) {
            holder.itemView.setOnClickListener{view ->
                itemClick!!.onClick(view, position)
            }
        }
        holder.bindContent(contents[position])
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindContent(item : RVContentModel){

            val tvContent : TextView = itemView.findViewById(R.id.tvContent)
            val imgContent : ImageView = itemView.findViewById(R.id.imgContent)

            tvContent.text = item.name

            Glide.with(context).load(item.imgURL).into(imgContent)

        }


    }
}