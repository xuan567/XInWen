package com.example.xinwen.Model

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.db.Item


class WeiBoAdapter(val weiboList: List<Item>):RecyclerView.Adapter<WeiBoAdapter.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val weiboText : TextView = view.findViewById(R.id.weibo_text)
        val weiboNum :TextView = view.findViewById(R.id.weibo_num)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.weibo_item, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weibo = weiboList.get(position)
        holder.weiboText.text = weibo.title
        holder.weiboNum.text = (position+1).toString()
        if(position==0){
            holder.weiboNum.setTextColor(Color.RED)
            holder.weiboNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==1){
            holder.weiboNum.setTextColor(Color.parseColor("#FF5722"))
            holder.weiboNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==2){
            holder.weiboNum.setTextColor(Color.parseColor("#FF9800"))
            holder.weiboNum.setTypeface(Typeface.DEFAULT_BOLD)
        }

        holder.itemView.setOnClickListener{
            if(mOnItemClickListener!=null){
                mOnItemClickListener?.onItemClick(it,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return weiboList.size
    }


}