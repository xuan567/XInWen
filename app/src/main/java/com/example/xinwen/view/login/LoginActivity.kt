package com.example.xinwen.view.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.xinwen.R
import com.example.xinwen.base.BaseActivity
import com.example.xinwen.databinding.ActivityLoginBinding
import com.example.xinwen.db.INPUTRIGHT
import com.example.xinwen.db.NOTFWTHEAUTOCODE
import com.example.xinwen.view.MainActivity

/**
 * author : Haa-zzz
 * time : 2021/8/1
 * 登录组件，集成BMob完成短信验证登录功能，使用 ViewBind+ViewModel实现
 */
class LoginActivity : BaseActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var phone : EditText
    private lateinit var code : EditText
    private lateinit var login : Button
    private lateinit var loading : ProgressBar
    private lateinit var authCode : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }
    private fun initView() {
        //获取ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.debugButton.setOnClickListener {
            MainActivity.startFromActivity(this)
            finish()
        }
    }
    private fun initData() {

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        phone = binding.username
        code = binding.password
        login = binding.login
        loading = binding.loading
        authCode = binding.authCode
        //获取输入并检测输入格式是否正确
        initInputData()
        initAuthCodeData()
        initVerifyData()
    }

    private fun initAuthCodeData() {
        authCode.setOnClickListener{
            NOTFWTHEAUTOCODE = false
            loginViewModel.loginSendCode(phone.text.toString().trim() )
        }
        loginViewModel.loginGetAutoCode.observe(this, Observer {
            val loginAutoCode = it ?: return@Observer
            if( loginAutoCode.error != null ){
                Toast.makeText(this,"发送失败",Toast.LENGTH_LONG).show()
            }else{
                login.isEnabled = true
            }
        })
        loginViewModel.loginCountNumber.observe(this, Observer {
            val countNumber = it ?: return@Observer
            if(countNumber.textCountNumber != null){
                authCode.text = countNumber.textCountNumber
            }
            authCode.setBackgroundResource(countNumber.textColor)
            authCode.isEnabled = countNumber.isEnable
        })
    }

    private fun initInputData() {
        //输入发生改变后，调用ViewModel中的方法区判断输入是否有异常
        phone.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                phone.text.toString().trim(),
                code.text.toString().trim()
            )
        }
        code.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                phone.text.toString().trim(),
                code.text.toString().trim()
            )
        }
        loginViewModel.loginFormState.observe(this, Observer {
            val loginState = it ?:  return@Observer
            //根据登录状态 设置 login按钮是否可以点击
            //login.isEnabled = loginState.isDataValid
            if (loginState.usernameError != null) {
                phone.error = getString(loginState.usernameError)
            }
            else {
                //可以发验证码了
                    if(NOTFWTHEAUTOCODE){
                        authCode.isEnabled = loginState.isUserNameValid
                        authCode.setBackgroundResource(R.color.authCode)
                    }
                INPUTRIGHT = loginState.isUserNameValid && loginState.isPasswordValid
            }
        })
    }

    private fun initVerifyData() {

        login.setOnClickListener{
            if(!INPUTRIGHT){
                showErrorToast(this,getString(R.string.inputError))
                return@setOnClickListener
            }else{
                //点击登录后设置 等待 可见
                loading.visibility = View.VISIBLE
                loginViewModel.loginVerificationResult(phone.text.toString().trim(),
                    code.text.toString().trim())
            }
        }
        loginViewModel.loginResult.observe(this, Observer {
            val result = it ?: return@Observer
            if(result.success){
                showSuccessToast(this,"登录成功")
                MainActivity.startFromActivity(this)
                finish()
            }else{
                showErrorToast(this,getString(R.string.sendAutoCodeError))
            }
        })
    }
    companion object {
        fun startFromActivity(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

}