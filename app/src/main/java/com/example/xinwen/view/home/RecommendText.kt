package com.example.xinwen.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.xinwen.R
import kotlinx.android.synthetic.main.fragment_recommend_text.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecommendText.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecommendText : Fragment() {
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

        return inflater.inflate(R.layout.fragment_recommend_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mtitle = arguments?.getString("title")
        val mpic = arguments?.getString("pic")
        val mcontent = arguments?.getString("content")
        val msrc = arguments?.getString("src")
        val mtime = arguments?.getString("time")
        Glide.with(this).load(mpic).into(pic)
        title.text = mtitle
        content.text = mcontent
        src.text = msrc
        time.text = mtime
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecommendText.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecommendText().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @JvmName("newInstance1")
    fun newInstance(pic:String, title:String,content:String,src:String,time:String) : RecommendText{
        val recommendText : RecommendText = RecommendText()
        val args : Bundle = Bundle()
        args.putString("pic",pic)
        args.putString("title",title)
        args.putString("content",content)
        args.putString("src",src)
        args.putString("time",time)
        recommendText.arguments = args
        return recommendText
    }
}