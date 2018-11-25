package com.example.jpec.streetint.utils

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.jpec.streetint.interfaces.CreatedWorkoutFocusListener


class ZoomCenterCardLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean, val createdWorkoutFocusListener: CreatedWorkoutFocusListener) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    private val mShrinkAmount = 0.5f
    private val mShrinkDistance = 0.75f
    private var oldMaxScalePos = -1

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled =  super.scrollHorizontallyBy(dx, recycler, state)
        val midpoint = width / 2f
        val d0 = 0f
        val d1 = mShrinkDistance * midpoint
        val s0 = 1f
        val s1 = 1f - mShrinkAmount

        var maxScale = -1f
        var maxScalePos = -1
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
            val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
            val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
            child.scaleX = scale
            child.scaleY = scale
            if (maxScale < scale)
            {
                maxScale = scale
                maxScalePos = i
            }
        }
        if (oldMaxScalePos != maxScalePos)
        {
            oldMaxScalePos = maxScalePos
            createdWorkoutFocusListener.onChangeFocus(maxScalePos == 1)
        }
        return scrolled
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        scrollVerticallyBy(0, recycler, state)
    }
}