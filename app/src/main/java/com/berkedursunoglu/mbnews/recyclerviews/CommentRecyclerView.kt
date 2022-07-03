package com.berkedursunoglu.mbnews.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.NewsdetailCommentCardviewBinding
import com.berkedursunoglu.mbnews.model.Comment

class CommentRecyclerView(val commentArray:List<Comment>): RecyclerView.Adapter<CommentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newsdetail_comment_cardview,parent,false)
        return CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.detail_comment_username.text = commentArray[position].userName.toString()
        holder.detail_comment_content.text = commentArray[position].content.toString()
    }

    override fun getItemCount(): Int {
        return commentArray.size
    }
}

class CommentHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    val detail_comment_imageview = itemView.findViewById<ImageView>(R.id.detail_comment_imageview)
    val detail_comment_username = itemView.findViewById<TextView>(R.id.detail_comment_username)
    val detail_comment_content = itemView.findViewById<TextView>(R.id.detail_comment_content)
}
