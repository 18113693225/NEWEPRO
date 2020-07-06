package com.app.android.epro.epro.ui.activity

import android.content.Intent
import android.view.View
import com.app.android.epro.epro.R
import com.app.android.epro.epro.base.BaseActivity
import com.app.android.epro.epro.mvp.contract.LoginContract
import com.app.android.epro.epro.mvp.model.bean.LoginBean
import com.app.android.epro.epro.mvp.presenter.LoginPresenter
import com.app.android.epro.epro.utils.CustomUtils
import com.app.android.epro.epro.utils.Preference
import com.bumptech.glide.Glide
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import top.wefor.circularanim.CircularAnim


class LoginActivity : BaseActivity(), LoginContract.View {


    private val mPresenter by lazy { LoginPresenter() }

    private var token: String by Preference("token", "")
    private var phone: String by Preference("phone", "")
    private var word: String by Preference("word", "")


    init {
        mPresenter.attachView(this)

    }


    private fun play() {
//        video.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.login_video))
//        video.start()
//        video.setOnCompletionListener {
//            it.start()
//        }
        Glide.with(this).asGif().load(R.drawable.bg_login_gif).into(bg_login)


    }


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
        if (phone.isNotEmpty() && word.isNotEmpty()) {
            phone_number.setText(phone)
            password.setText(word)
        }
    }

    override fun initView() {
        loginButton.setOnClickListener {

            if (phone_number.text.toString().isEmpty() ||
                password.text.toString().isEmpty()
            ) {
                Toasty.error(this, "请输入账号密码").show()

            } else {

                phone = phone_number.text.toString()
                word = password.text.toString()

                val params = HashMap<String, String>()
                params["userAccountName"] = phone_number.text.toString()
                params["userAccountPwd"] = password.text.toString()
                mPresenter.requestLoginData(CustomUtils.toBody(params))

            }

        }


    }


    override fun start() {
        play()
    }

    override fun onRestart() {
        super.onRestart()
        play()
    }

    override fun setLoginData(loginBean: LoginBean) {

        when (loginBean.code) {
            0 -> {
                token = loginBean.data.token
                CircularAnim.fullActivity(this, loginButton)
                    .colorOrImageRes(R.color.color_anim)
                    .go {
                        toMain()
                    }
            }
            else -> {
                Toasty.error(this, loginBean.message).show()
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        Toasty.error(this, msg).show()
    }

    override fun showLoading() {
        CircularAnim.hide(loginButton)
            .endRadius((progressBar.height / 2).toFloat())
            .go {
                progressBar.visibility = View.VISIBLE
            }
    }

    override fun dismissLoading() {
        CircularAnim.show(loginButton).go()
    }

    private fun toMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}