package com.keephealth.app.biz;

import com.keephealth.app.entity.ViewDetailInfo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created  on 2017/6/29.
 * author:悟.静
 * 作用:
 */

public class DeatailDataManager {
    public static ViewDetailInfo getlist(Document document) {
        ViewDetailInfo message = new ViewDetailInfo();
        Element element = document.select("div#main").first();
        if(element==null){
            return message;
        }
        Element element1=element.select("div.left").first();
        if(element1==null){
            return message;
        }
        Element element2=element1.select("div#content").first();
        if(element2==null){
            return message;
        }
        String introduce = element.select("div.kw").first().text();
        String imageUrl=element2.select("div").last().select("img").attr("src");
        String txt = element1.select("p").html();
        String intro=element1.select("div.intro").first().text();
        message.setContent(txt);
        message.setIntro(intro);
        message.setImgeUrl(imageUrl);
        message.setIntroduce(introduce);
        return message;
    }
}
