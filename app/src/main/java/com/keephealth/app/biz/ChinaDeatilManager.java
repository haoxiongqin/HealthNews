package com.keephealth.app.biz;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created  on 2017/6/28.
 * author:悟.静
 * 作用:老中医养生网的详细内容
 */

public class ChinaDeatilManager {
    public static String getArticleBean(Document document){
        String text="";
        Element element=document.select("div#outline").first().select("div#pageMain").first();
        if(element!=null){
             text=element.select("div#archivesContent").first().html();
        }
        return text;
    }
}
