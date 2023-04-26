package com.example.xinwen.view.garbageClassification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.xinwen.databinding.FragmentGarbageClassificationBinding
import com.example.xinwen.view.home.IndicatorLineUtil
import com.example.xinwen.view.home.MyPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GarbageClassificationFragment : Fragment() {

    private var _binding: FragmentGarbageClassificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGarbageClassificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        val viewpager : ViewPager2 = binding.viewpager
        val tabLayout : TabLayout = binding.tabLayout
        val titles = arrayListOf("识别", "指南")

        val fragmentLists : List<Fragment> = listOf(IficationFragment.newInstance(), GarbageGuideFragment.newInstance())
        val fragmentManager : FragmentManager = activity?.supportFragmentManager ?:return

        val adapter = MyPagerAdapter(lifecycle, fragmentManager, fragmentLists)

        viewpager.adapter = adapter
        TabLayoutMediator(tabLayout, viewpager, true){
                tab, position ->
            tab.text = titles[position]
        }.attach()

        tabLayout.post {
            IndicatorLineUtil.setIndicator(tabLayout, 20, 20)
        }
    }
}