package cn.miaole.aircraft_booking_android.views.arcmenu2

import android.animation.Animator
import android.content.Context
import android.os.Build
import android.view.MotionEvent
import android.view.View
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.*

/**
 * 这是一个弧形展开菜单控件
 * Created by sunny on 17-12-29.
 */
class ArcMenu private constructor(context: Context, val animator: ArcAnimator, position: Position,
                                  size: Int, contentMargin: Int,
                                  layoutMarginHorizon: Int,
                                  layoutMarginVertical: Int,
                                  bgRes: Int, contentRes: Int,
                                  private var duration: Long)
    : FloatingButton(context, position, size, contentMargin, layoutMarginHorizon,
        layoutMarginVertical, bgRes, contentRes), View.OnClickListener {

    private constructor(builder: Builder) : this(builder.context, builder.animator, builder.position,
            builder.size, builder.contentMargin, builder.layoutMarginHorizon, builder.layoutMarginVertical,
            builder.bgRes, builder.contentRes, builder.duration) {
        this.setOnClickListener {
            toogle(object : OnArcMenuChangeListener {
                override fun open() {
                }

                override fun close() {
                }

            })
        }
    }

    companion object {
        fun build(context: Context) = Builder(context)
                .build()
    }

    /**
     * 子菜单按钮
     */
    private val items = mutableListOf<FloatingButton>()
    /**
     * 起始角度
     */
    var startAngle = 315
    /**
     * 终止角度
     */
    var endAngle = 45
    /**
     * 弧形半径
     */
    var radius = 300

    /**
     * 子菜单监听，按添加的顺序标识
     */
    var onArcMenuItemClickListener: OnArcMenuItemClickListener? = null

    /**
     * 菜单的状态
     */
    private var status = Status.CLOSED

    fun addItem(size: Int = DEFAULT_SIZE, contentMargin: Int = DEFAULT_CONTENT_MARGIN,
                bgRes: Int = R.drawable.round_circle_bg, contentRes: Int = -1): ArcMenu {
        val floatingButton = FloatingButton(context, this.position, size, contentMargin,
                ((this.size - size) / 2 + this.layoutMarginHorizon),
                ((this.size - size) / 2 + this.layoutMarginVertical), bgRes, contentRes)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            floatingButton.transitionName = position.toString()
        }
        floatingButton.visibility = View.GONE
        floatingButton.alpha = 0f
        floatingButton.id = items.size
        floatingButton.setOnClickListener(this)
        //添加的时候隐藏
        items.add(floatingButton)
        return this
    }


    fun isOpen(): Boolean = (status == Status.OPENED || status == Status.OPEN_ING)

    /**
     * 处于关闭则展开，处于展开则关闭, 处于动画中则不处理
     */
    fun toogle(listener: OnArcMenuChangeListener) {
        if (status == Status.OPENED) {
            close()
            listener.close()
        } else if (status == Status.CLOSED) {
            open()
            listener.open()
        }
    }

    fun isClickSubItem(ev: MotionEvent): Boolean {
        return isOpen() && items.any {
            ev.isInView(it)
        }
    }


    /**
     * 展开菜单
     */
    fun open() {
        if (status == Status.OPENED || status == Status.OPEN_ING)
            return
        this.rotate(0f, 225f, duration = duration)
        val span = (endAngle - startAngle) % 360
        //记录动画完成的item数
        var finish = 0
        for ((index, item) in items.withIndex()) {
            item.visibility = visibility
            animator.openAnimator(startAngle, endAngle, radius, span, index, items.size, item,
                    object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {

                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            finish++
                            if (finish == items.size)
                                status = Status.OPENED
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            finish++
                            if (finish == items.size)
                                status = Status.OPENED
                        }

                        override fun onAnimationStart(animation: Animator?) {
                            if (finish == 0)
                                status = Status.OPEN_ING
                        }

                    })
        }
    }


    /**
     * 关闭菜单
     */
    fun close() {
        if (status == Status.CLOSED || status == Status.CLOSE_ING)
            return
        this.rotate(225f, 0f, duration = duration)
        val span = endAngle - startAngle % 360
        //记录动画完成的item数
        var finish = 0
        for ((index, item) in items.withIndex()) {
            animator.closeAnimator(startAngle, endAngle, radius, span, index, items.size, item,
                    object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {

                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            finish++
                            item.visibility = View.GONE
                            if (finish == items.size)
                                status = Status.CLOSED
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            finish++
                            if (finish == items.size)
                                status = Status.CLOSED
                        }

                        override fun onAnimationStart(animation: Animator?) {
                            if (finish == 0)
                                status = Status.CLOSE_ING
                        }

                    })
        }
    }


    /**
     * 菜单展开状态变化监听器
     */
    interface OnArcMenuChangeListener {
        fun open()
        fun close()
    }


    override fun onClick(v: View?) {
        if (status == Status.OPENED)
            onArcMenuItemClickListener?.onClick(v?.id ?: -1, v)
    }

    /**
     * 子菜单项点击回调
     */
    interface OnArcMenuItemClickListener {
        fun onClick(position: Int, v: View?)
    }

    /**
     * 状态
     */

    enum class Status {
        CLOSE_ING, OPEN_ING, CLOSED, OPENED
    }

    /**
     * 动画接口
     */
    interface ArcAnimator {
        fun openAnimator(startAngle: Int, endAngle: Int, radius: Int, span: Int, index: Int,
                         size: Int, item: FloatingButton, mListener: Animator.AnimatorListener)

        fun closeAnimator(startAngle: Int, endAngle: Int, radius: Int, span: Int, index: Int,
                          size: Int, item: FloatingButton, mListener: Animator.AnimatorListener)
    }


    /**
     * ArcMenu 构造器
     */
    class Builder constructor(val context: Context) {
        var position: Position = DEFAULT_POSITION
        var size: Int = DEFAULT_SIZE
        var contentMargin: Int = DEFAULT_CONTENT_MARGIN
        var layoutMarginHorizon: Int = DEFAULT_LAYOUT_MARGIN
        var layoutMarginVertical: Int = DEFAULT_LAYOUT_MARGIN
        var bgRes: Int = R.drawable.round_circle_bg
        var contentRes: Int = -1
        var duration: Long = 300
        //设置默认动画
        var animator: ArcAnimator = object : ArcAnimator {
            override fun closeAnimator(startAngle: Int, endAngle: Int, radius: Int, span: Int,
                                       index: Int, size: Int, item: FloatingButton,
                                       mListener: Animator.AnimatorListener) {
                val increment = span.toFloat() / (size - 1)
                item.transRotateAlpha(
                        transX = floatArrayOf(easySin(startAngle + increment * index, radius).toFloat(), 0f),
                        transY = floatArrayOf(-easyCos(startAngle + increment * index, radius).toFloat(), 0f),
                        duration = this@Builder.duration,
//                    rotation = floatArrayOf(360f, 0f),
                        alpha = floatArrayOf(1f, 0f),
                        listener = mListener)
            }

            override fun openAnimator(startAngle: Int, endAngle: Int, radius: Int, span: Int,
                                      index: Int, size: Int, item: FloatingButton,
                                      mListener: Animator.AnimatorListener) {
                val increment = span.toFloat() / (size - 1)
                item.transRotateAlpha(
                        transX = floatArrayOf(0f, easySin(startAngle + increment * index, radius).toFloat()),
                        transY = floatArrayOf(0f, -easyCos(startAngle + increment * index, radius).toFloat()),
                        duration = this@Builder.duration,
//                    rotation = floatArrayOf(0f, 360f),
                        alpha = floatArrayOf(0f, 1f),
                        listener = mListener
                )

            }

        }


        fun position(position: Position): Builder {
            this.position = position
            return this
        }

        fun size(size: Int): Builder {
            this.size = size
            return this
        }

        fun contentMargin(contentMargin: Int): Builder {
            this.contentMargin = contentMargin
            return this
        }

        fun layoutMarginHorizon(layoutMarginHorizon: Int): Builder {
            this.layoutMarginHorizon = layoutMarginHorizon
            return this
        }

        fun layoutMarginVertical(layoutMarginVertical: Int): Builder {
            this.layoutMarginVertical = layoutMarginVertical
            return this
        }

        fun bgRes(bgRes: Int): Builder {
            this.bgRes = bgRes
            return this
        }

        fun contentRes(contentRes: Int): Builder {
            this.contentRes = contentRes
            return this
        }

        fun animator(animator: ArcAnimator): Builder {
            this.animator = animator
            return this
        }

        fun duration(duration: Long): Builder {
            this.duration = duration
            return this
        }

        fun build() = ArcMenu(this)
    }
}