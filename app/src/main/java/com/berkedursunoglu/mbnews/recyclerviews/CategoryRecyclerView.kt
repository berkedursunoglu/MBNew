package com.berkedursunoglu.mbnews.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.model.News
import com.berkedursunoglu.mbnews.model.NewsItem
import com.berkedursunoglu.mbnews.news.CategoryNewsDirections
import com.squareup.picasso.Picasso

class CategoryRecyclerView(var categoryArrayList: List<NewsItem>) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_news_cardview, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Picasso.get().load(categoryArrayList[position].imageUrl).resize(128, 128).centerCrop()
            .into(holder.category_news_imageview)
        holder.category_news_content.text = categoryArrayList[position].content
        holder.category_news_header.text = categoryArrayList[position].newsName
        holder.itemView.setOnClickListener {
            val action = CategoryNewsDirections.actionCategoryNewsToNewsPageDetail(categoryArrayList[position].infoId,2)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val category_news_imageview = itemView.findViewById<ImageView>(R.id.category_news_imageview)
    val category_news_header = itemView.findViewById<TextView>(R.id.category_news_header)
    val category_news_content = itemView.findViewById<TextView>(R.id.category_news_content)
}
