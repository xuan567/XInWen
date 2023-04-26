package com.example.xinwen.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.xinwen.R
import com.example.xinwen.viewModel.SearchViewModel
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
        dashboardViewModel.searchLiveData.observe(requireActivity()) {
            creator.text = it.creator
            from.text = it.from
            hitokoto.text = it.hitokoto

        }
    }
}


