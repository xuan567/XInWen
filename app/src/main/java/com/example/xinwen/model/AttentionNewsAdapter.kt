package com.example.xinwen.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.R.layout.attention_news_item

class AttentionNewsAdapter(val newsList: List<String>):RecyclerView.Adapter<AttentionNewsAdapter.ViewHolder>(){

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val newsText: TextView = view.findViewById(R.id.news_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(attention_news_item,parent,false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.newsText.text = news

    }

    override fun getItemCount() = newsList.size

}