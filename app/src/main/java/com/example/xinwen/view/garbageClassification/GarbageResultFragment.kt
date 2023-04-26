package com.example.xinwen.view.garbageClassification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.xinwen.bean.GarbageRecognitionBean
import com.example.xinwen.databinding.FragmentGarbageResultListBinding

class GarbageResultFragment : Fragment() {

    private var _binding: FragmentGarbageResultListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: IficationViewModel
    private lateinit var know: Button
    private lateinit var recyclerView: RecyclerView

    private var type: Int? = 0
    private var date: ArrayList<GarbageRecognitionBean.TextItem>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGarbageResultListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[IficationViewModel::class.java]
        initDate()
        initView()
        initListener()
    }

    private fun initDate() {
        val bundle = arguments
        if (bundle != null) {
            type = bundle.getInt("type")
            //date = bundle.getParcelableArrayList("item", GarbageRecognitionBean.TextItem::class.java)
        }
    }

    private fun initListener() {
        know.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        know = binding.know
        recyclerView = binding.recyclerItem
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            val date = IficationFragment.dateList?.result?.list ?: emptyList()
            Log.d("GarbageResultFragment", "initView:  $date")
            this.adapter = MyGarbageResultRecyclerViewAdapter(date)
        }
    }

    companion object {

        val typeMap: HashMap<Int, String> = hashMapOf()
        init {
            typeMap[0] = "可回收垃圾"
            typeMap[1] = "有害垃圾"
            typeMap[2] = "厨余垃圾"
            typeMap[3] = "其他垃圾"
        }


    }
}