package com.lifeng.refreshandload.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名称：RefreshAndLoad
 * 类描述：长短提示的封装类
 * 作者：峰哥
 * 创建时间：2016/11/30 10:47
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class ToastUtils {

    public static void longToast(Context context, String message){
        if(context!=null&&message!=null){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static void shortToast(Context context,String message){
        if(context!=null&&message!=null){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
    }

}
