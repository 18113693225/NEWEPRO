package com.app.android.epro.epro.ui.activity

import android.Manifest
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.app.android.epro.epro.MyApplication
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.utils.AppUtils
import com.app.android.epro.epro.utils.CustomUtils
import com.app.android.epro.epro.utils.Preference
import kotlinx.android.synthetic.main.activity_launch.*
import pub.devrel.easypermissions.EasyPermissions

class LaunchActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null
    private var language: String by Preference("language", "")

    override fun layoutId(): Int {
        return R.layout.activity_launch
    }

    override fun initData() {

    }

    override fun initView() {

        language = if (resources.configuration.locale.language == "zh") {
            "ZH"
        } else {
            "EN"
        }

        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"

        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                CustomUtils.toLogin(this@LaunchActivity)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })

        checkPermission()
    }

    override fun start() {

    }


    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val perms = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        EasyPermissions.requestPermissions(this, "应用需要以下权限,请允许", 0, *perms)

    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ) {
                    if (alphaAnimation != null) {
                        iv_app.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

}