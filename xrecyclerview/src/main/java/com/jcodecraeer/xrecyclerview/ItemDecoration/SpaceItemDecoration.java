package com.jcodecraeer.xrecyclerview.ItemDecoration;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class SpaceItemDecoration extends XRecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
        if (view == parent.getChildAt(0)){
            outRect.top = space;
        }
    }
}