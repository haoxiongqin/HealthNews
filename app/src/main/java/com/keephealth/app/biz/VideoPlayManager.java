package com.keephealth.app.biz;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created  on 2017/7/3.
 * author:悟.静
 * 作用:
 */

public class VideoPlayManager  {
    public static  String  getVideoUrl(Document document){
        Element element=document.select("div.all").first().select("div.content_left").first().select("div.con_main").first();
        Element element1=element.select("div#news_main").first().select("p").get(2);
        Element element2=element1.select("object").first();
        String url=element2.select("embed").first().attr("src");
        return url;
    }
}
