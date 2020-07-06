package com.app.android.epro.epro.view

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.VideoView


class LoginVideoView : VideoView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(0, widthMeasureSpec);
        val height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);

    }

    override fun setOnPreparedListener(l: MediaPlayer.OnPreparedListener?) {
        super.setOnPreparedListener(l)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

}