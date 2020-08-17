package com.sanmidev.yetanotherrecipeapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.top = spaceHeight
        outRect.left = spaceHeight
        outRect.right = spaceHeight
        outRect.bottom = spaceHeight
    }

}
