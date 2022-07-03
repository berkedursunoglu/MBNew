package com.berkedursunoglu.mbnews.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.model.NewsItem
import com.berkedursunoglu.mbnews.news.NewsPageDirections
import com.squareup.picasso.Picasso

class PublicNewsRecyclerView(var newsArrayList:ArrayList<NewsItem>) : RecyclerView.Adapter<PublicViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.mainpage_news_horizontal_cardview,parent,false)
        return PublicViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublicViewHolder, position: Int) {
        holder.newsHeader.text = newsArrayList[position].newsName
        Picasso.get().load(newsArrayList[position].imageUrl).resize(170,140).centerCrop().into(holder.newsImage)

        holder.itemView.setOnClickListener {
            val action = NewsPageDirections.actionNewsPageToNewsPageDetail(newsArrayList[position].infoId,1)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }
}

class PublicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val newsImage = itemView.findViewById<ImageView>(R.id.cardbviewNewsImage)
    val newsHeader = itemView.findViewById<TextView>(R.id.cardviewNewsHeader)
}

