package com.sqq.util;

public class HtmlTextSpecialDealUtil {

    public static void main(String[] args) {
        // 原html文件
        String orignalHtmlText = "<a href=\"/\" id=\"result_logo\" onmousedown=\"return c({'fm':'tab','tab':'logo'})\"><img class='index-logo-src' src=\"//www.baidu.com/img/flexible/logo/pc/result.png\" alt=\"到百度首页\" title=\"到百度首页\"><img class='index-logo-srcnew' src=\"//www.baidu.com/img/flexible/logo/pc/result@2.png\" alt=\"到百度首页\" title=\"到百度首页\"><img class='index-logo-peak' src=\"//www.baidu.com/img/flexible/logo/pc/peak-result.png\" alt=\"到百度首页\" title=\"到百度首页\"></a><a class=\"title-content tag-width c-link c-font-medium c-line-clamp1\" href=\"https://www.baidu.com/s?cl=3&amp;tn=baidutop10&amp;fr=top1000&amp;wd=%E5%8C%97%E4%BA%AC%E5%86%AC%E5%A5%A5%E9%97%AD%E5%B9%95+2026%E7%B1%B3%E5%85%B0%E8%A7%81&amp;rsv_idx=2&amp;rsv_dl=fyb_n_homepage&amp;sa=fyb_n_homepage&amp;hisfilter=1\" target=\"_blank\" ><span class=\"title-content-index c-index-single c-index-single-hot1\">1</span><span class=\"title-content-title\">北京冬奥闭幕 2026米兰见</span></a><span>牛年贺岁，登录设置新春皮肤！</span><li><a href=\"javascript:;\" name=\"ime_hw\">手写</a></li><li><a href=\"javascript:;\" name=\"ime_py\">拼音</a></li><li class=\"ln\"></li>";
        System.out.println("---01 orignalHtmlText: \n" + orignalHtmlText);

        // 1、删除最后一个a标签
        String dealedHtmlText = deleteTextLastTag(orignalHtmlText, "<a");
        System.out.println("---02 deleteTextLastTag, orignalHtmlText: \n" + orignalHtmlText);

        testStr();

        // 2、搜索span标签，判断标签含有删除"往期精彩回顾" 或者 "你对哪条新闻最感兴趣？欢迎分享评论"，如果有删除父节点

        // 3、删除"你对哪条新闻最感兴趣？欢迎分享评论！"

        // 4、判断是否存在标签 wx-view，如果是删除父节点,(即收听电台)




        System.out.println("---02 dealedHtmlText:\n" + orignalHtmlText);




    }

    /**
     * 删除最后一个指定标签
     * @param htmlText
     * @param tag
     * @return
     */
    private static String deleteTextLastTag(String htmlText, String tag){
        String dealedStr = htmlText;
        int lastIndex = dealedStr.lastIndexOf(tag);
        System.out.println("----1: lastIndex: " + lastIndex);
        System.out.println("----2: indexOf: " + dealedStr.indexOf(tag));


        return dealedStr;
    }

    public static void testStr(){
        String str = "sfsgegggsfs";
        System.out.println("----1: lastIndex: " + str.lastIndexOf("sf"));
        System.out.println("----2: indexOf: " + str.indexOf("sf"));
    }

}
