package com.almas.batuapps.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import android.widget.RelativeLayout
import com.almas.batuapps.R

class ForegroundRelativeLayout(context: Context, attrs: AttributeSet):RelativeLayout(context, attrs) {
    private var foreground: Drawable? = null

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.ForegroundView)
        val d = a.getDrawable(R.styleable.ForegroundView_android_foreground)
        if (d != null){
            setForeground(d)
        }
        a.recycle()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            outlineProvider = ViewOutlineProvider.BOUNDS
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if(foreground !=null) {
            foreground!!.setBounds(0,0,w,h)
        }
    }

    override fun hasOverlappingRendering(): Boolean {
        return false
    }

    override fun verifyDrawable(who: Drawable): Boolean {
        return super.verifyDrawable(who) || who === foreground
    }

    override fun jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState()
        if (foreground != null) foreground!!.jumpToCurrentState()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (foreground != null && foreground!!.isStateful) {
            foreground!!.state = drawableState
        }
    }

    override fun getForeground(): Drawable? {
        return foreground
    }

    override fun setForeground(drawable: Drawable) {
        if(foreground !== drawable) {
            if (foreground != null) {
                foreground!!.callback = null
                unscheduleDrawable(foreground)
            }
                foreground = drawable

            if (foreground != null) {
                foreground!!.setBounds(left, top, right, bottom)
                setWillNotDraw(false)
                foreground!!.callback = this
                if(foreground!!.isStateful) {
                        foreground!!.state = drawableState
                }
            } else {
                setWillNotDraw(true)
            }
            invalidate()
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        if (foreground != null) {
                foreground!!.draw(canvas)
            }
    }

    override fun drawableHotspotChanged(x: Float, y: Float) {
        super.drawableHotspotChanged(x, y)
        if (foreground != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                foreground!!.setHotspot(x,y)
            }
        }
    }
}