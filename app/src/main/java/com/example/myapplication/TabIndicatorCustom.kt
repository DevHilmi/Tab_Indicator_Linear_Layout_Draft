package com.example.myapplication

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlin.math.roundToInt


class TabIndicatorCustom @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    val listOfTab: MutableList<View> = mutableListOf()
    var currentActiveTab: Int = -1

    fun addIndicator() {
        val indicator = View(context)
        indicator.setBackgroundColor(Color.BLACK)
        val params = generateDefaultLayoutParams()
        params.width = dpToPx(16)
        params.height = dpToPx(4)
        params.leftMargin = dpToPx(2)
        params.rightMargin = dpToPx(2)
        addView(indicator, params)
        listOfTab.add(indicator)
    }

    fun setSelected(position: Int) {
        listOfTab.forEachIndexed { index, view ->
            if (position == index) {
                val initialWidth = view.width
                val targetWidth = dpToPx(30)

                val valueAnimator = ValueAnimator.ofInt(initialWidth, targetWidth)
                valueAnimator.duration = 200
                valueAnimator.addUpdateListener { animator ->
                    val newWidth = animator.animatedValue as Int
                    val layoutParams = view.layoutParams
                    layoutParams.width = newWidth
                    view.layoutParams = layoutParams
                    view.setBackgroundColor(Color.BLUE)
                }



                valueAnimator.start()
                val colorValue = ValueAnimator.ofObject(ArgbEvaluator(), Color.BLACK, Color.BLUE)
                colorValue.duration = 200
                colorValue.addUpdateListener { animator ->
                    val newColor = animator.animatedValue as Int
                    view.setBackgroundColor(newColor)
                }
                colorValue.start()
            } else if (index == currentActiveTab) {
                val initialWidth = view.width
                val targetWidth = dpToPx(16)

                val valueAnimator = ValueAnimator.ofInt(initialWidth, targetWidth)
                valueAnimator.duration = 200
                valueAnimator.addUpdateListener { animator ->
                    val newWidth = animator.animatedValue as Int
                    val layoutParams = view.layoutParams
                    layoutParams.width = newWidth
                    view.layoutParams = layoutParams
                    view.setBackgroundColor(Color.BLACK)
                }
                valueAnimator.start()
                val colorValue = ValueAnimator.ofObject(ArgbEvaluator(), Color.BLUE, Color.BLACK)
                colorValue.duration = 200
                colorValue.addUpdateListener { animator ->
                    val newColor = animator.animatedValue as Int
                    view.setBackgroundColor(newColor)
                }
                colorValue.start()
            }
        }
        Log.d("HILMI", "CUR " + currentActiveTab + " POS " + position)
        currentActiveTab = position
    }

    private fun dpToPx(dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}