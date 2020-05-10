package com.arshak.freeiptv.screens.home.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class SimpleItemDecorator extends RecyclerView.ItemDecoration {

    int mSpace;
    boolean mIsHorizontalLayout;

    public SimpleItemDecorator(int space) {
        mSpace = space;
    }

    public SimpleItemDecorator(int space, boolean isHorizontalLayout) {
        mSpace = space;
        mIsHorizontalLayout = isHorizontalLayout;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        if (mIsHorizontalLayout) {
            outRect.right = mSpace;
            outRect.left = mSpace;
        } else {
            outRect.bottom = mSpace;
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = mSpace;
            else
                outRect.top = 0;

        }
    }
}