package com.lifeng.refreshandload.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lifeng.refreshandload.adapter.BaseRecyclerViewAdapter;

/**
 * 项目名称：RefreshAndLoad
 * 类描述：自定义的上拉加载的 RecyclerView
 * 作者：峰哥
 * 创建时间：2016/12/3 14:08
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class BaseRecyclerView extends RecyclerView {

    //最后一个可见的布局的位置
    private int lastVisibleItemPosition;
    private onLoadMoreListener listener;
    private boolean isInit;//数组是否初始化
    private BaseRecyclerViewAdapter baseRecyclerViewAdapter;
    private float startY;//手指开始的位置
    private float endY;//手指结束的位置
    public static boolean isLoading;//避免重复加载

    public BaseRecyclerView(Context context) {
        this(context,null,0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Adapter adapter = getAdapter();
        if (!(adapter instanceof BaseRecyclerViewAdapter)) {
            throw new IllegalArgumentException("the adapter must extents BaseRecyclerViewAdapter");
        }
        baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) adapter;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }else if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] last = null;
            if (!isInit) {
                last = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                isInit = true;
            }
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(last);
            for (int i : lastVisibleItemPositions) {
                lastVisibleItemPosition = i > lastVisibleItemPosition ? i : lastVisibleItemPosition;
            }
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //当正在执行加载的操作时，屏蔽掉多余的加载操作，直至该加载完成之后执行第二次加载的操作
                if(!isLoading){
                    endY=ev.getY();
                    //此时滑动到底部并且为上拉的动作，执行加载的操作方法
                    if(( endY-startY) < 0 && lastVisibleItemPosition == baseRecyclerViewAdapter.getItemCount() -1 ){
                        if(listener==null){
                            break;
                        }
                        listener.loadMore();
                        isLoading=true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                startY=0;
                endY=0;
                break;
            case MotionEvent.ACTION_CANCEL:
                startY=0;
                endY=0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface onLoadMoreListener{
        void loadMore();
    }

    public void setListener(onLoadMoreListener listener){
        this.listener=listener;
    }

}
