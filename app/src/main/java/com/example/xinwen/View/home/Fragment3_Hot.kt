package com.example.xinwen.View.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinwen.Model.OnClickListener
import com.example.xinwen.Model.WeiBoAdapter
import com.example.xinwen.R
import com.example.xinwen.ViewModel.HotListViewModel
import kotlinx.android.synthetic.main.fragment_hot.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment3_hot.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment3_hot : Fragment() {
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
         val hotListViewModel = ViewModelProvider(this).get(HotListViewModel::class.java)
        hotListViewModel.hot()
        hotListViewModel.hotListLivaData.observe(requireActivity(), {
            if (it == null) {
                Log.d("Hot", "Hot: it==null")
                return@observe
            }
            val layoutManager = LinearLayoutManager(context)

            val adapter = WeiBoAdapter(it.list)
            hot_recycler.layoutManager = layoutManager
            hot_recycler.adapter = adapter
            hot_recycler.setItemViewCacheSize(50)
            adapter.setOnItemClickListener(object : WeiBoAdapter.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    val string = it.list.get(position).link
                    intent.data = Uri.parse(string)
                    startActivity(intent)
                }

            })

        })

        return inflater.inflate(R.layout.fragment_hot, container, false)
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val trasaction = fragmentManager?.beginTransaction()
        trasaction?.replace(R.id.hot_Layout,fragment)
        trasaction?.addToBackStack(null)
        trasaction?.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daily1.setOnClickListener {
            replaceFragment(ZhiHuListFragment())
        }
        daily2.setOnClickListener {
            replaceFragment(TouTiaoListFragment())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment3_hot.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment3_hot().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

