package com.lifeng.refreshandload.utils;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.lifeng.refreshandload.view.BaseRecyclerView;

/**
 * 项目名称：RefreshAndLoad
 * 类描述：动画工具类
 * 作者：峰哥
 * 创建时间：2016/12/3 15:35
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class AnimationUtils {

    //底部提示信息的显示动画以及隐藏
    public static void showAndHide(final TextView textView , String message){
        textView.setVisibility(View.VISIBLE);
        textView.setText(message);
        textView.setAnimation(moveToViewLocation());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.GONE);
                textView.setAnimation(moveToViewBottom());
                BaseRecyclerView.isLoading=false;
            }
        },2000);
    }

    /**
     * 从控件所在位置移动到控件的底部
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation hiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        hiddenAction.setDuration(500);
        return hiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation showAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAction.setDuration(500);
        return showAction;
    }

}
