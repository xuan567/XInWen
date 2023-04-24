package com.example.xinwen.model

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.db.ZhiHuItem

class ZhiHuAdapter(val zhihuList : List<ZhiHuItem>) : RecyclerView.Adapter<ZhiHuAdapter.ViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }



    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val zhihuNum : TextView = view.findViewById(R.id.zhihu_num)
        val zhihuText : TextView = view.findViewById(R.id.zhihu_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZhiHuAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.zhihu_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZhiHuAdapter.ViewHolder, position: Int) {
        val zhihu = zhihuList.get(position)
        holder.zhihuNum.text = (position+1).toString()
        holder.zhihuText.text = zhihu.title

        if(position==0){
            holder.zhihuNum.setTextColor(Color.RED)
            holder.zhihuNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==1){
            holder.zhihuNum.setTextColor(Color.parseColor("#FF5722"))
            holder.zhihuNum.setTypeface(Typeface.DEFAULT_BOLD)
        }
        if(position==2){
            holder.zhihuNum.setTextColor(Color.parseColor("#FF9800"))
            holder.zhihuNum.setTypeface(Typeface.DEFAULT_BOLD)
        }

        holder.itemView.setOnClickListener {
            if(mOnItemClickListener!=null){
                mOnItemClickListener?.onItemClick(it,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return zhihuList.size
    }
}