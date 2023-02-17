package com.example.communityapplication.contents

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.communityapplication.R
import com.example.communityapplication.utils.FirebaseAuthUtil
import com.example.communityapplication.utils.FirebaseRefUtil

class RVContentsAdapter(
    val context: Context,
    val contents: MutableList<RVContentModel>,
    val contentsKeyList: MutableList<String>,
    val bookmarkIDList: MutableList<String>
) :
    RecyclerView.Adapter<RVContentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVContentsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_contents_item, parent, false)

        Log.d(this::class.java.toString(), contentsKeyList.toString())
        Log.d(this::class.java.toString(), bookmarkIDList.toString())

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVContentsAdapter.ViewHolder, position: Int) {
        holder.bindContent(contents[position], contentsKeyList[position])
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindContent(item: RVContentModel, key: String) {

            itemView.setOnClickListener {
                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()

                val intentToContentWebActivity = Intent(context, ContentWebActivity::class.java)
                intentToContentWebActivity.putExtra("websiteURL", item.websiteURL)


                itemView.context.startActivity(intentToContentWebActivity)
            }

            val tvContent: TextView = itemView.findViewById(R.id.tvContent)
            val imgContent: ImageView = itemView.findViewById(R.id.imgContent)
            val imgBookmark: ImageView = itemView.findViewById(R.id.imgBookmark)

            if(bookmarkIDList.contains(key)) {
                imgBookmark.setImageResource(R.drawable.bookmark_color)
            }else {
                imgBookmark.setImageResource(R.drawable.bookmark_white)
            }


            imgBookmark.setOnClickListener {
                Log.d(this::class.java.toString(), FirebaseAuthUtil.getUID())

                Toast.makeText(context, key, Toast.LENGTH_SHORT).show()

                FirebaseRefUtil.bookmarkRef.child(FirebaseAuthUtil.getUID()).child(key)
                    .setValue(BookmarkModel(true))

            }

            tvContent.text = item.name

            Glide.with(context).load(item.imgURL).into(imgContent)

        }


    }
}