package com.keephealth.app.biz;

import com.keephealth.app.entity.SearchBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/6/28.
 * author:悟.静
 * 作用:养生网食谱
 */

public class CookBookManager {
    public static List<SearchBean> getCooKBook(Document document){
        List<SearchBean> bean=new ArrayList<>();
        Element Element1=document.select("div.all").first().select("div.main_ys_list").first().select("div.main_list_left").first();
        if(Element1==null){
            return bean;
        }
        Elements Element=Element1.select("div.width735").first().select("ul").first().select("li");
        if(Element==null){
            return bean;
        }
        for(Element element:Element){
            String img=element.select("a").first().select("img").attr("abs:src");
            String date=element.select("span").first().select("i").text();
            String titleUrl=element.select("span").first().select("a").attr("href");
            String title=element.select("span").first().select("a").text();
            String content=element.select("p").first().text();
            String mask=element.select("em").select("a").text();
            SearchBean ss=new SearchBean();
            ss.setType(mask);
            ss.setIntro(content);
            ss.setTitle(title);
            ss.setDate(date);
            ss.setHref(titleUrl);
            ss.setClick(img);
            bean.add(ss);
        }
        return bean;
    }
}
