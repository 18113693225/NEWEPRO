package com.app.android.epro.epro.ui.adapter


import android.content.Context
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import com.app.android.epro.epro.R
import com.app.android.epro.epro.mvp.model.bean.UserInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.layout.util.SmartUtil


class HomeMenuAdapter(data: MutableList<UserInfoBean.Data.Menu>) :
    BaseQuickAdapter<UserInfoBean.Data.Menu, BaseViewHolder>(R.layout.home_menu, data) {


    override fun convert(holder: BaseViewHolder, item: UserInfoBean.Data.Menu) {

        val screenWidth: Int = getScreenWidth(context)
        val relativeLayout: RelativeLayout = holder.getView(R.id.rl_container)
        val params: ViewGroup.LayoutParams = relativeLayout.layoutParams
        params.width = SmartUtil.dp2px(screenWidth.toFloat()) / 4
        relativeLayout.layoutParams = params


        if ("zh" == context.resources.configuration.locale.language) {
            holder.setText(R.id.name, item.menuNameTxt[0].menuNam)
        } else {
            holder.setText(R.id.name, item.menuNameTxt[1].menuNam)
        }
        when (item.menuCode) {
            "MEN_CHINCK_IN_PUNCH" -> holder.setImageResource(R.id.img, R.mipmap.ic_attendance)
            "MEN_FLOW_APPROVAL" -> holder.setImageResource(R.id.img, R.mipmap.ic_audit)
            "MENU_DINGING_ROOM_ORDER_FOOD" -> holder.setImageResource(R.id.img, R.mipmap.ic_food)
            "MENU_PERSONAL_OFFICE" -> holder.setImageResource(R.id.img, R.mipmap.ic_task)
            else -> holder.setImageResource(R.id.img, R.mipmap.ic_food)
        }

    }

    private fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels // 屏幕宽度（像素）
        return SmartUtil.px2dp(width).toInt() - 30


    }



}