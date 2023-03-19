package com.example.xinwen.View.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.xinwen.R
import com.example.xinwen.ViewModel.HomeAttentionViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.OkHttpClient

class HomeFragment : Fragment() {

    private lateinit var homeAttentionViewModel: HomeAttentionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeAttentionViewModel =
                ViewModelProvider(this).get(HomeAttentionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val viewpager : ViewPager2 = root.findViewById(R.id.viewpager)!!
        val tablayout : TabLayout = root.findViewById(R.id.tab_layout)!!
        val titles = arrayListOf<String>("关注", "推荐", "热榜", "西安")

        val attention : Fragment = fragment1_attention()
        val recommend : Fragment = fragment2_recommend()
        val hot : Fragment = fragment3_hot()
        val xian : Fragment = fragment4_xian()

        val fragmentLists : List<Fragment> = listOf<Fragment>(attention, recommend, hot, xian)
        val fragmentManager : FragmentManager = activity?.supportFragmentManager!!
        val adapter : MyPagerAdapter = MyPagerAdapter(lifecycle, fragmentManager, fragmentLists)

        viewpager.adapter = adapter
        TabLayoutMediator(tablayout, viewpager, true){
            tab, position -> tab.setText(titles[position])
        }.attach()

        tablayout.post(Runnable {
            IndicatorLineUtil.setIndicator(tablayout,20,20)
            });


        return root



    }

}


