package com.example.xinwen.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinwen.model.RecommendAdapter
import com.example.xinwen.R
import com.example.xinwen.viewModel.RecommendViewModel
import kotlinx.android.synthetic.main.fragment2_recommend.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment2_recommend.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment2_recommend : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val recommendViewModel = ViewModelProvider(this).get(RecommendViewModel::class.java)
        recommendViewModel.recommend()
        recommendViewModel.recommendLiveData.observe(requireActivity(),{
            if(it==null){
                Log.d("Recommend","it==null")
            }
            val layoutManager = LinearLayoutManager(context)
            val adapter = context?.let { it1 -> RecommendAdapter(it1,it.list) }
            recommend_recycler.layoutManager = layoutManager
            recommend_recycler.adapter = adapter

            adapter?.setOnItemClickListener(object : RecommendAdapter.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    val arr = it.list[position]
                    val recommendText = RecommendText().newInstance(arr.pic,arr.title,arr.content,arr.src,arr.time)
                    replaceFragment(recommendText)
                }

            })
        })

        return inflater.inflate(R.layout.fragment2_recommend, container, false)
    }


    private fun replaceFragment(fragment:Fragment){
        val fragmentManager  = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.recommend_layout,fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment2_recommend.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment2_recommend().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}