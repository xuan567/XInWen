package com.example.xinwen.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.xinwen.R
import com.example.xinwen.db.ListItem

class RecommendAdapter(val context : Context, val recommendLists: List<ListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val TYPE_ITEM_ONE = 1
    val TYPE_ITEM_TWO = 2
    val TYPE_ITEM_THREE = 3

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    //创建三种ViewHolder
    private inner class OneViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val item1Text : TextView = view.findViewById(R.id.item1_text)
        val item1Image : ImageView = view.findViewById(R.id.item1_pc)
        val item1Time : TextView = view.findViewById(R.id.item1_time)
    }
    private inner class TwoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val item2Text : TextView = view.findViewById(R.id.item2_text)
        val item2Image : ImageView = view.findViewById(R.id.item2_pc)
        val item2Time : TextView = view.findViewById(R.id.item2_time)
    }
    private inner class ThreeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val item3Text : TextView = view.findViewById(R.id.item3_text)
        val item3Image1 : ImageView = view.findViewById(R.id.item3_pc1)
        val item3Image2 : ImageView = view.findViewById(R.id.item3_pc2)
        val item3Image3 : ImageView = view.findViewById(R.id.item3_pc3)
        val item3Time : TextView = view.findViewById(R.id.item3_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_ONE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_one, parent, false)
            return OneViewHolder(view)
        } else if (viewType == TYPE_ITEM_TWO) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_two, parent, false)
            return TwoViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_three, parent, false)
            return ThreeViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val recommend = recommendLists[position]
        if (holder is OneViewHolder) {
            holder.item1Text.text = recommend.title
            holder.item1Time.text = "${recommend.src} + ${recommend.time}"
            Glide.with(context).load(recommend.pic).into(holder.item1Image)
        }else if(holder is TwoViewHolder){
            holder.item2Text.text = recommend.title
            Glide.with(context).load(recommend.pic).into(holder.item2Image)
            holder.item2Time.text = "${recommend.src} + ${recommend.time}"
        }else if (holder is ThreeViewHolder){
            holder.item3Text.text = recommend.title
            Glide.with(context).load(recommend.pic).into(holder.item3Image1)
            Glide.with(context).load(recommend.pic).into(holder.item3Image2)
            Glide.with(context).load(recommend.pic).into(holder.item3Image3)
            holder.item3Time.text = "${recommend.src} + ${recommend.time}"
        }

        holder.itemView.setOnClickListener {
            if(mOnItemClickListener!=null){
                mOnItemClickListener?.onItemClick(it,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return recommendLists.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 3 == 1) {
            return TYPE_ITEM_ONE
        } else if (position % 3 == 2) {
            return TYPE_ITEM_TWO
        } else {
            return TYPE_ITEM_THREE
        }
    }
}

