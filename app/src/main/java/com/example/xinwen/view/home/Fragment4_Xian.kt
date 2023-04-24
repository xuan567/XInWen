package com.example.xinwen.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xinwen.model.ClothesAdapter
import com.example.xinwen.R
import com.example.xinwen.viewModel.ClothesViewModel
import com.example.xinwen.viewModel.XianWeatherViewModel
import kotlinx.android.synthetic.main.fragment_xian.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment4_xian.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment4_xian : Fragment() {
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


    lateinit var weatherViewModel : XianWeatherViewModel
    lateinit var clothesViewModel : ClothesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        weatherViewModel = ViewModelProvider(this).get(XianWeatherViewModel::class.java)
        weatherViewModel.weather()
        weatherViewModel.weatherLiveData.observe(requireActivity(),{
            if(it==null){
                Log.d("TAG","weather==null")
                return@observe
            }
            weather_text1.text = it.basic.location
            weather_text2.text = "${it.now.cond_txt}  ${it.now.tmp}${"度"}  "
            weather_text3.text = it.update.loc
        })


        clothesViewModel = ViewModelProvider(this).get(ClothesViewModel::class.java)
        clothesViewModel.clothes()
        clothesViewModel.clothesLiveData.observe(requireActivity(),{
            if(it==null){
                Log.d("TAG","clothes==null")
                return@observe
            }
            val layoutManager = LinearLayoutManager(context)
            val adapter = ClothesAdapter(it.lifestyle)
            weather_recycler.layoutManager = layoutManager
            weather_recycler.adapter = adapter
        })
        return inflater.inflate(R.layout.fragment_xian, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment4_xian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment4_xian().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


