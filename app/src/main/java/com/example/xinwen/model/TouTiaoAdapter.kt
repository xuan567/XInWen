package com.example.xinwen.model

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.db.TouTiaoItem

class TouTiaoAdapter(val toutiaoList : List<TouTiaoItem>) : RecyclerView.Adapter<TouTiaoAdapter.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val toutiaoNum : TextView = view.findViewById(R.id.toutiao_num)
        val toutiaoText : TextView = view.findViewById(R.id.toutiao_text)
        val toutiaoOther : TextView = view.findViewById(R.id.toutiao_other)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.toutiao_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val toutiao = toutiaoList.get(position)
        holder.toutiaoNum.text = (position+1).toString()
        holder.toutiaoText.text = toutiao.title
        holder.toutiaoOther.text = toutiao.other

        if(position==0){
            holder.toutiaoNum.setTextColor(Color.RED)
            holder.toutiaoNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==1){
            holder.toutiaoNum.setTextColor(Color.parseColor("#FF5722"))
            holder.toutiaoNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==2){
            holder.toutiaoNum.setTextColor(Color.parseColor("#FF9800"))
            holder.toutiaoNum.setTypeface(Typeface.DEFAULT_BOLD)
        }

        holder.itemView.setOnClickListener {
            if(mOnItemClickListener!=null){
                mOnItemClickListener?.onItemClick(it,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return toutiaoList.size
    }
}