package com.pcl.mvvm.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import com.pcl.mvvm.R
import com.pcl.mvvm.databinding.ActivityLoginBinding
import com.pcl.mvvm.databinding.ActivityMainBinding
import com.pcl.mvvm.ui.me.MeViewModel
import com.pcl.mvvm.utils.logi
import com.pcl.mvvm.utils.logv

class LoginActivity : BaseActivity<LoginModel, ActivityLoginBinding>() {
    private val meviewmodel: MeViewModel by viewModels()
    override fun layoutId() = R.layout.activity_login
    override fun initView(savedInstanceState: Bundle?) {
        mBinding.viewmodel = viewModel

        mBinding.click = ProxyClick()
        meviewmodel.getPopularWeb()
        meviewmodel.popularWeb.observe(this, Observer {
            it.toString().logv()
        })
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