package com.example.xinwen.View.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.xinwen.R
import com.example.xinwen.ViewModel.HomeAttentionViewModel
import com.example.xinwen.ViewModel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var dashboardViewModel: SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboardViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        dashboardViewModel.search()
        dashboardViewModel.searchLiveData.observe(requireActivity(),{
            creator.text = it.creator
            from.text = it.from
            hitokoto.text = it.hitokoto

        })
    }
}


