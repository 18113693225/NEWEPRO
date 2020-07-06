package com.app.android.epro.epro.view


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable

import android.util.AttributeSet

import android.view.animation.LinearInterpolator
import com.app.android.epro.epro.R


class NbButton : androidx.appcompat.widget.AppCompatButton {
    private var widths = 0
    private var heights = 0
    private var backDrawable: GradientDrawable = GradientDrawable()
    private var isMorphing = false
    private var startAngle = 0
    private var paint: Paint = Paint()
    private var arcValueAnimator: ValueAnimator = ValueAnimator()

    private var animatorSet: AnimatorSet = AnimatorSet()


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        val colorDrawable: Int = resources.getColor(R.color.cutePink)
        backDrawable!!.setColor(colorDrawable)
        backDrawable!!.cornerRadius = 120f
        background = backDrawable
        text = "登陆"

        paint.color = resources.getColor(R.color.color_white)
        paint.strokeWidth = 8F
        paint.style = Paint.Style.STROKE
        paint.textSize = 2F
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY) {
            widths = widthSize
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            heights = heightSize
        }
    }

    fun stopAnim() {
        isMorphing = false
        animatorSet.cancel()
        background = backDrawable
        text = "登陆"

    }

    fun startAnim() {
        isMorphing = true
        text = ""
        val valueAnimator = ValueAnimator.ofInt(widths, heights)
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            val leftOffset = (widths - value) / 2
            val rightOffset = widths - leftOffset
            backDrawable!!.setBounds(leftOffset, 0, rightOffset, heights)
        }
        val objectAnimator =
            ObjectAnimator.ofFloat(backDrawable, "cornerRadius", 120f, heights / 2.toFloat())
        animatorSet = AnimatorSet()
        animatorSet.duration = 500
        animatorSet.playTogether(valueAnimator, objectAnimator)
        animatorSet.start()

        //画中间的白色圆圈
        showArc()
    }


    private fun showArc() {
        arcValueAnimator = ValueAnimator.ofInt(0, 1080)
        arcValueAnimator.addUpdateListener(AnimatorUpdateListener { animation ->
            startAngle = animation.animatedValue as Int
            invalidate()
        })
        arcValueAnimator.interpolator = LinearInterpolator()
        arcValueAnimator.repeatCount = ValueAnimator.INFINITE
        arcValueAnimator.duration = 3000
        arcValueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isMorphing) {
            val rectF = RectF(
                (width / 2 - 30).toFloat(),
                (heights / 2 - 30).toFloat(),
                (width / 2 + 30).toFloat(),
                (heights / 2 + 30).toFloat()
            )
            paint?.let { canvas.drawArc(rectF, startAngle.toFloat(), 270F, false, it) }
        }
    }
}