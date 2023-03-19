package com.example.xinwen.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xinwen.R
import com.example.xinwen.db.Lifestyle

class ClothesAdapter(val clothesList : List<Lifestyle>) : RecyclerView.Adapter<ClothesAdapter.ViewHolder> (){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val typeText : TextView = view.findViewById(R.id.weather_type)
        val brfText : TextView = view.findViewById(R.id.weather_brf)
        val txtText : TextView = view.findViewById(R.id.weather_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.xian_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val clothes = clothesList[position]
        holder.typeText.text = clothes.type
        holder.txtText.text  = clothes.txt
        holder.brfText.text = clothes.brf

    }

    override fun getItemCount(): Int {
        return clothesList.size
    }


}