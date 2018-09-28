package cn.miaole.aircraft_booking_android.views.arcmenu2

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.MyDelegates

/**
 * 这是一个悬浮按钮控件，只能通过Java代码实例化
 * 可以设置按钮的背景，按钮大小，位置，内容资源，内容的边距，按钮的边距
 * @author Ming.J
 * Created by sunny on 17-12-29.
 */
open class FloatingButton(context: Context, protected val position: Position = DEFAULT_POSITION,
                          protected val size: Int = DEFAULT_SIZE, contentMargin: Int = DEFAULT_CONTENT_MARGIN,
                          protected val layoutMarginHorizon: Int = DEFAULT_LAYOUT_MARGIN,
                          protected val layoutMarginVertical: Int = DEFAULT_LAYOUT_MARGIN,
                          bgRes: Int = R.drawable.round_circle_bg,
                          contentRes: Int = -1) : FrameLayout(context) {
    companion object {
        const val DEFAULT_SIZE = 120
        const val DEFAULT_CONTENT_MARGIN = 10
        const val DEFAULT_LAYOUT_MARGIN = 5
        val DEFAULT_POSITION = Position.POSITION_BOTTOM_CENTER
    }

    private lateinit var contentView: View

    enum class Position {
        POSITION_TOP_LEFT, POSITION_TOP_CENTER, POSITION_TOP_RIGHT,
        POSITION_CENTER_LEFT, POSITION_CENTER, POSITION_CENTER_RIGHT,
        POSITION_BOTTOM_LEFT, POSITION_BOTTOM_CENTER, POSITION_BOTTOM_RIGHT
    }

    init {
        val layoutParams = this.setPosition(position, size, layoutMarginHorizon,
                layoutMarginVertical)
        this.setBackgroundResource(bgRes)
        val imgIcon = ImageView(context)
        if (contentRes > 0)
            imgIcon.setImageResource(contentRes)
        updateContentView(imgIcon, null,
                contentMargin)
        this.attach(layoutParams)
        this.isClickable = true
    }

    private fun setPosition(position: Position, size: Int, layoutMarginHorizon: Int = DEFAULT_LAYOUT_MARGIN,
                            layoutMarginVertical: Int = DEFAULT_LAYOUT_MARGIN): LayoutParams {
        val layoutParams = FrameLayout.LayoutParams(size, size)
        var gravity by MyDelegates.notNullAndOnlyInitFirstTime<Int>()
        when (position) {
            Position.POSITION_BOTTOM_RIGHT -> {
                gravity = Gravity.BOTTOM or Gravity.END
            }
            Position.POSITION_BOTTOM_CENTER -> {
                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            }
            Position.POSITION_BOTTOM_LEFT -> {
                gravity = Gravity.BOTTOM or Gravity.START
            }
            Position.POSITION_CENTER -> {
                gravity = Gravity.CENTER
            }
            Position.POSITION_CENTER_LEFT -> {
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
            }
            Position.POSITION_CENTER_RIGHT -> {
                gravity = Gravity.END or Gravity.CENTER_VERTICAL
            }
            Position.POSITION_TOP_CENTER -> {
                gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            }
            Position.POSITION_TOP_LEFT -> {
                gravity = Gravity.TOP or Gravity.START
            }
            Position.POSITION_TOP_RIGHT -> {
                gravity = Gravity.TOP or Gravity.END
            }
        }
        layoutParams.gravity = gravity
        layoutParams.setMargins(layoutMarginHorizon, layoutMarginVertical, layoutMarginHorizon, layoutMarginVertical)
        return layoutParams
    }

    private fun updateContentView(contentView: View, contentParams: LayoutParams? = null, contentMargin: Int) {
        this.contentView = contentView
        var params by MyDelegates.notNullAndOnlyInitFirstTime<LayoutParams>()
        if (contentParams == null) {
            params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
            params.setMargins(contentMargin, contentMargin, contentMargin, contentMargin)
        } else {
            params = contentParams
        }
        params.gravity = Gravity.CENTER

        contentView.isClickable = false
        this.addView(contentView, params)
    }

    private fun attach(layoutParams: FrameLayout.LayoutParams) {
        (getActivityContentView() as ViewGroup).addView(this, layoutParams)
    }

    fun detach() {
        (getActivityContentView() as ViewGroup).removeView(this)
    }


    private fun getActivityContentView(): View {
        try {
            return (context as Activity).window.decorView.findViewById(android.R.id.content)
        } catch (e: ClassCastException) {
            throw ClassCastException("Please provide an Activity context for this FloatingButton！！")
        }
    }


}