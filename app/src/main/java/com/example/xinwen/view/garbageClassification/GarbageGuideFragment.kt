package com.example.xinwen.view.garbageClassification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.xinwen.R
import com.example.xinwen.bean.GarbageRecognitionBean

class GarbageGuideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_garbage_guide, container, false)
    }

    companion object {

        fun newInstance() = GarbageGuideFragment()

    }
}