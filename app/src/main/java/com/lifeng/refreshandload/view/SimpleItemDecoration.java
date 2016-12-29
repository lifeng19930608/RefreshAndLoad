package com.lifeng.refreshandload.view;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 项目名称：RefreshAndLoad
 * 类描述：对适配器的item布局的装饰
 * 作者：峰哥
 * 创建时间：2016/11/30 16:54
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class SimpleItemDecoration extends RecyclerView.ItemDecoration{

    private int space;
    private int span;

    public SimpleItemDecoration(int space, int span) {
        this.space = space;
        this.span = span;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        if ( parent.getChildAdapterPosition(view)==0||parent.getChildLayoutPosition(view) == parent.getAdapter().getItemCount()) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top=0;
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            int spanIndex = lp.getSpanIndex();
            //判断当前的位置，如果是最后设置右边距
            if (spanIndex == span-1) {
                outRect.left = space;
                outRect.right = space;
            } else {//其余的位置右边距为0
                outRect.left = space;
                outRect.right = 0;
            }
        } else if (parent.getLayoutManager() instanceof GridLayoutManager){
            if (parent.getChildLayoutPosition(view) % span == 0) {
                outRect.left = space;
                outRect.right = space;
            } else {
                outRect.left = space;
                outRect.right = 0;
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager){
            outRect.left=space;
            outRect.right=space;
        }
    }

}
