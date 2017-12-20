package com.rosinante.nyobaanko.util

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Rosinante24 on 20/12/17.
 */
class SpaceItemDeclaration(context: Context, dimenRes: Int) : RecyclerView.ItemDecoration() {
    private val space: Int = context.resources.getDimensionPixelOffset(dimenRes)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        when (getOrientation(parent)) {
            LinearLayoutManager.HORIZONTAL -> if (position != 0) outRect.top = space
            LinearLayoutManager.VERTICAL -> if (position != 0) outRect.left = space
        }
    }

    private fun getOrientation(parent: RecyclerView): Int {
        val linearlayoutmanager = parent.layoutManager
        return (linearlayoutmanager as? LinearLayoutManager)?.orientation ?: -1
    }

}