package com.example.xinwen.base

import android.app.Application
import android.content.Context
import cn.bmob.v3.Bmob
import com.example.xinwen.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initFlipper()
        initBMob()
        context = this

    }

    private fun initBMob() {
        Bmob.initialize(this, "9f834138a1d94429688b240dfd27f432")
    }

    private fun initFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }
    }
    companion object{
        private lateinit var context: BaseApplication
        @JvmStatic
        fun getContext() : Context{
            return context
        }
    }

}