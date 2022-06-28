package com.pcl.mvvm.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import com.pcl.mvvm.R
import com.pcl.mvvm.databinding.ActivityLoginBinding
import com.pcl.mvvm.databinding.ActivityMainBinding

class LoginActivity : BaseActivity<LoginModel, ActivityLoginBinding>() {

    override fun layoutId() = R.layout.activity_login
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.viewmodel = viewModel

        mBinding.click = ProxyClick()
    }
    inner class ProxyClick {

        fun clear() {
            viewModel.username.set("")
        }

        fun login() {
            when {
                viewModel.username.get().isEmpty() -> showMessage("请填写账号")
                viewModel.password.get().isEmpty() -> showMessage("请填写密码")
            }
        }
        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isShowPwd.set(isChecked)
            }
    }
    override fun initData() {
    }
}