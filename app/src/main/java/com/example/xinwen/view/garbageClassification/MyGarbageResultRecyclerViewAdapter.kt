package com.example.xinwen.view.garbageClassification

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.bean.GarbageRecognitionBean
import com.example.xinwen.databinding.FragmentGarbageResultBinding


class MyGarbageResultRecyclerViewAdapter(private val values: List<GarbageRecognitionBean.TextItem>)
    : RecyclerView.Adapter<MyGarbageResultRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentGarbageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val type = GarbageResultFragment.typeMap[item.type ?: item.lajitype]
        holder.sort.text = type
        holder.name.text = item.name
        if (type.equals("厨余垃圾")) {
            holder.imageTwo.setImageResource(R.drawable.kitchen)
        }
        if (type.equals("其他垃圾")) {
            holder.imageTwo.setImageResource(R.drawable.other)
        }
        if (type.equals("有害垃圾")) {
            holder.imageTwo.setImageResource(R.drawable.nonrecyclable)
        }
        if (type.equals("可回收垃圾")) {
            holder.imageTwo.setImageResource(R.drawable.recyclable)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentGarbageResultBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.wasteName
        val sort: TextView = binding.wasteSort
        val imageTwo: ImageView = binding.imageTwo
    }

}