package com.example.xinwen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xinwen.R
import com.example.xinwen.base.BaseActivity
import com.example.xinwen.db.SpHelp
import com.example.xinwen.view.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        GlobalScope.launch(Dispatchers.Main) {
            delay(500)
            if (SpHelp.queryIsLoggedIn()) {
                MainActivity.startFromActivity(this@StartActivity)
            } else {
                LoginActivity.startFromActivity(this@StartActivity)
            }
            delay(500)
            //完成
            finish()
        }
    }


}