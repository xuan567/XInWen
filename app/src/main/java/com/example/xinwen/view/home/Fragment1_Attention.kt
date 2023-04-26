package com.example.xinwen.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.xinwen.model.AttentionNewsAdapter
import com.example.xinwen.R
import com.example.xinwen.viewModel.HomeAttentionViewModel
import kotlinx.android.synthetic.main.fragment1_attention.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment1_attention.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment1_attention : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    lateinit var attentionViewModel:HomeAttentionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        attentionViewModel = ViewModelProvider(this).get(HomeAttentionViewModel::class.java)
        attentionViewModel.attention()
        attentionViewModel.attentionLiveData.observe(requireActivity()) {
            if (it == null) {
                Log.d("TAG", "onCreateView: it==null")
                return@observe
            }
            Glide.with(this).load(it.head_image).into(attention_picture)
            attention_time.text = it.date
            val layoutManager = LinearLayoutManager(context)
            attention_recycler.layoutManager = layoutManager
            val newsList = it.news as List<String>
            val adapter = AttentionNewsAdapter(newsList)
            attention_recycler.adapter = adapter
        }
        return inflater.inflate(R.layout.fragment1_attention, container, false)



    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment1_attention().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }

//    private val viewModel by lazy {
//        ViewModelProvider(this).get(HomeViewModel::class.java)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}