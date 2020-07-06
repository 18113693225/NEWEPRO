package com.app.android.epro.epro.base


import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.app.android.epro.epro.R
import com.getbase.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.include_detail_bar.*

abstract class BaseDetailActivity : AppCompatActivity() {


    lateinit var dialog: MaterialDialog
    lateinit var refuseBt: FloatingActionButton
    lateinit var passBt: FloatingActionButton
    lateinit var freezeBt: FloatingActionButton
    lateinit var thawBt: FloatingActionButton
    lateinit var backBt: FloatingActionButton
    lateinit var replayBt: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        showBackButton()
        showFreezeButton()
        showInputDialog()
        showPassButton()
        showRefuseButton()
        showReplayButton()
        showThawButton()
        initView()
        start()

    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()


    /**
     * 添加拒绝按钮
     */

    private fun showRefuseButton() {
        refuseBt =
            FloatingActionButton(baseContext)
        refuseBt.setColorNormalResId(R.color.details_fab_color_no_pass_normal)
        refuseBt.setColorPressedResId(R.color.details_fab_color_no_pass_press)
        refuseBt.setIcon(R.mipmap.ic_fab_no_pass)
    }

    fun addRefuseButton() {
        multiple_actions.addButton(refuseBt)
    }


    /**
     * 添加同意按钮
     */

    private fun showPassButton() {
        passBt =
            FloatingActionButton(baseContext)
        passBt.setColorNormalResId(R.color.details_fab_color_pass_normal)
        passBt.setColorPressedResId(R.color.details_fab_color_pass_press)
        passBt.setIcon(R.mipmap.ic_fab_pass)
    }

    fun addPassButton() {
        multiple_actions.addButton(passBt)
    }

    /**
     * 添加冻结按钮
     */

    private fun showFreezeButton() {
        freezeBt =
            FloatingActionButton(baseContext)
        freezeBt.setColorNormalResId(R.color.details_fab_color_freeze_normal)
        freezeBt.setColorPressedResId(R.color.details_fab_color_freeze_press)
        freezeBt.setIcon(R.mipmap.ic_fab_freeze)
    }

    fun addFreezeButton() {
        multiple_actions.addButton(freezeBt)
    }

    /**
     * 添加解冻按钮
     */

    private fun showThawButton() {
        thawBt =
            FloatingActionButton(baseContext)
        thawBt.setColorNormalResId(R.color.details_fab_color_thaw_normal)
        thawBt.setColorPressedResId(R.color.details_fab_color_thaw_press)
        thawBt.setIcon(R.mipmap.ic_fab_thaw)
    }


    fun addThawButton() {
        multiple_actions.addButton(thawBt)
    }

    /**
     * 添加退回按钮
     */

    private fun showBackButton() {
        backBt =
            FloatingActionButton(baseContext)
        backBt.setColorNormalResId(R.color.details_fab_color_back_normal)
        backBt.setColorPressedResId(R.color.details_fab_color_back_press)
        backBt.setIcon(R.mipmap.ic_fab_back)
    }

    fun addBackButton() {
        multiple_actions.addButton(backBt)
    }

    /**
     * 添加回复按钮
     */
    private fun showReplayButton() {
        replayBt =
            FloatingActionButton(baseContext)
        replayBt.setColorNormalResId(R.color.details_fab_color_replay_normal)
        replayBt.setColorPressedResId(R.color.details_fab_color_replay_press)
        replayBt.setIcon(R.mipmap.ic_fab_replay)
    }

    fun addReplayButton() {

        multiple_actions.addButton(replayBt)
    }


    private fun showInputDialog() {

        dialog = MaterialDialog(this)
            .title(R.string.approval_opinions)
            .cancelOnTouchOutside(false)
            .customView(R.layout.dialog_approval_opinions)


    }


}