package com.example.xinwen.View.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinwen.Model.ZhiHuAdapter
import com.example.xinwen.R
import com.example.xinwen.ViewModel.ZhiHuViewModel
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.toutiaot_list.*
import kotlinx.android.synthetic.main.zhihu_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ZhiHuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZhiHuListFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val zhiHuViewModel = ViewModelProvider(this).get(ZhiHuViewModel::class.java)
        zhiHuViewModel.zhihu()
        zhiHuViewModel.zhihuLiveData.observe(requireActivity(),{
            if(it==null){
                Log.d("Hot", "Hot: it==null")
                return@observe
            }

            val layoutManager = LinearLayoutManager(context)
            val adapter = ZhiHuAdapter(it.list)
            zhihu_recyclerView.layoutManager = layoutManager
            zhihu_recyclerView.adapter = adapter
            zhihu_recyclerView.setItemViewCacheSize(50)
            adapter.setOnItemClickListener(object :ZhiHuAdapter.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    val string = it.list.get(position).link
                    intent.data = Uri.parse(string)
                    startActivity(intent)
                }
            })

        })
        return inflater.inflate(R.layout.zhihu_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ZhiHuListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ZhiHuListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}