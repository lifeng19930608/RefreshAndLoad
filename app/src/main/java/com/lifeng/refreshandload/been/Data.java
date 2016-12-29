package com.lifeng.refreshandload.been;

/**
 * 项目名称：RefreshAndLoad
 * 类描述：数据的封装类
 * 作者：峰哥
 * 创建时间：2016/12/1 11:04
 * 邮箱：470794349@qq.com
 * 修改简介：
 */
public class Data {

    private String url;//图片的url;
    private String text;//文本的标题

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
