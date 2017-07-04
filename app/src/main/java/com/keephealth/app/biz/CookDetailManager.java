package com.keephealth.app.biz;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created  on 2017/6/29.
 * author:悟.静
 * 作用:
 */

public class CookDetailManager  {
    public static String getCookDetail(Document document){
        String text="";
        Element element1=document.select("div.all").first().select("div.content_left").first();
        if(element1==null){
            return text;
        }
        Element element2=element1.select("div.con_main").select("div#news_main").first();
        if(element2==null){
            return text;
        }
        text=element2.select("p").html();
        return  text;
    }
}
