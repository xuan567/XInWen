package com.example.xinwen.view.garbageClassification

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.xinwen.R
import com.example.xinwen.bean.GarbageRecognitionBean
import com.example.xinwen.databinding.FragmentGarbageIdentificationBinding
import com.example.xinwen.util.CameraUtils
import com.example.xinwen.util.ImageUtil
import com.example.xinwen.util.RecordingUtil
import com.example.xinwen.view.base.ButtomDialog
import com.example.xinwen.view.base.RecordView


class IficationFragment : Fragment() {

    private lateinit var viewModel: IficationViewModel

    private var _binding: FragmentGarbageIdentificationBinding? = null
    private val binding get() = _binding!!

    private var dialog: ButtomDialog? = null
    private lateinit var editText: EditText
    private lateinit  var search: TextView
    private lateinit var camera: View
    private lateinit var say: View
    private lateinit var recordView: RecordView
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var text4: TextView
    private lateinit var text5: TextView
    private lateinit var text6: TextView
    private lateinit var text7: TextView
    private lateinit var text8: TextView

    /**
     * startActivityForResult()方法废弃,google更加建议使用Activity Result API来实现在两个Activity之间交换数据的功能。
     * 内置Contract:更简单实现权限申请,拍照，打开文件等
     * 参考：https://blog.csdn.net/guolin_blog/article/details/121063078?spm=1001.2014.3001.5501
     */
    private val choosePhotoRequestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            openAlbum()
        } else {
            Toast.makeText(requireContext(), "获取权限失败，请打开相关权限", Toast.LENGTH_LONG).show()
        }
    }

    private val audioRecordRequestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {

        } else {
            Toast.makeText(requireContext(), "获取权限失败，请打开相关权限", Toast.LENGTH_LONG).show()
        }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        Log.d(TAG, "takePictureLauncher: ${bitmap?.byteCount ?: 0}")
        bitmap ?: return@registerForActivityResult
        val imageView = binding.testImage
        Glide.with(requireActivity()).load(bitmap).into(imageView)
        viewModel.getGarbageTypeByBase64Image(ImageUtil.base64Encode(bitmap))
    }

    private val choosePhotoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val bitmap = result.data?.let { CameraUtils.getImageBitMapApi29Down(it,requireContext()) }
            Log.d(TAG, "choosePhotoLauncher: ${bitmap?.byteCount ?: 0}")
            bitmap ?: return@registerForActivityResult
            val imageView = binding.testImage
            Glide.with(requireActivity()).load(bitmap).into(imageView)
            viewModel.getGarbageTypeByBase64Image(ImageUtil.base64Encode(bitmap))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGarbageIdentificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[IficationViewModel::class.java]
        initView()
        initListener()
        initDate()
    }

    private fun initView() {
        editText = binding.searchEdit
        search = binding.searchItem
        camera = binding.searchCamera
        say = binding.searchSay
        recordView = binding.recordView

        text1 = binding.text1
        text2 = binding.text2
        text3 = binding.text3
        text4 = binding.text4
        text5 = binding.text5
        text6 = binding.text6
        text7 = binding.text7
        text8 = binding.text8


    }

    private fun initListener() {
        text1.setOnClickListener {
            setAllText(text1.text.toString().trim())
        }
        text2.setOnClickListener {
            setAllText(text2.text.toString().trim())
        }
        text3.setOnClickListener {
            setAllText(text3.text.toString().trim())
        }
        text4.setOnClickListener {
            setAllText(text4.text.toString().trim())
        }
        text5.setOnClickListener {
            setAllText(text5.text.toString().trim())
        }
        text6.setOnClickListener {
            setAllText(text6.text.toString().trim())
        }
        text7.setOnClickListener {
            setAllText(text7.text.toString().trim())
        }

        camera.setOnClickListener {
            val builder: ButtomDialog.Builder = ButtomDialog.Builder(context)
            //添加条目，可多个
            builder.addMenu("相机") {
                dialog?.cancel()
                takePhotos()
            }.addMenu("相册") {
                dialog?.cancel()
                choosePhoto()
            }
            builder.setTitle("请选择方式") //添加标题
            builder.setCanCancel(true) //点击阴影时是否取消dialog，true为取消
            builder.setShadow(true) //是否设置阴影背景，true为有阴影
            builder.setCancelText("取消") //设置最下面取消的文本内容

            builder.setCancelListener {
                dialog!!.cancel()
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show()
            }
            dialog = builder.create()
            dialog?.show()
        }


        search.setOnClickListener {
            val word = editText.text.toString().trim()
            if (word.isNotBlank()) {
                viewModel.getGarbageTypeByText(word)
            }
        }
        say.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        recordView.visibility = View.VISIBLE
                        RecordingUtil.startRecordAudio()
                    }
                    MotionEvent.ACTION_UP -> {
                        recordView.visibility = View.GONE
                        RecordingUtil.stopRecordAudio()
                    }

                    else -> {}
                }
                return true
            }
        })
    }


    private fun choosePhoto() {
        choosePhotoRequestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun openAlbum() {
        choosePhotoLauncher.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
    }


    private fun takePhotos() {
        takePictureLauncher.launch(null)
    }


    private fun setAllText(text: String?) {
        if (text.isNullOrBlank()) return
        editText.setText(text)
        viewModel.getGarbageTypeByText(text)
    }

    private fun initDate() {
        viewModel.garbageText.observe(viewLifecycleOwner) {
            Log.d(TAG, "garbageText: $it")
            val bundle = bundleOf("type" to TYPE_TEXT, "item" to it.result.list)
            dateList = it
            findNavController().navigate(R.id.action_garbageClassificationFragment_to_garbageResultFragment, bundle)
        }
        viewModel.garbageImage.observe(viewLifecycleOwner) {
            Log.d(TAG, "garbageImage: $it")
            val bundle = bundleOf("type" to TYPE_IMAGE)
            dateList = it
            findNavController().navigate(R.id.action_garbageClassificationFragment_to_garbageResultFragment, bundle)
        }
        viewModel.garbageVideo.observe(viewLifecycleOwner) {
            Log.d(TAG, "garbageVideo: $it")
            val bundle = bundleOf("type" to TYPE_VIDEO)
            dateList = it
            findNavController().navigate(R.id.action_garbageClassificationFragment_to_garbageResultFragment, bundle)
        }

    }

    companion object {
        const val TYPE_TEXT: Int= 0
        const val TYPE_IMAGE: Int = 1
        const val TYPE_VIDEO: Int = 2

        fun newInstance() = IficationFragment()
        const val TAG = "IficationFragment"

        var dateList: GarbageRecognitionBean? = null

    }

}