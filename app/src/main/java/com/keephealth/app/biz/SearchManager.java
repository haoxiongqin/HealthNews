package com.keephealth.app.biz;


import com.keephealth.app.entity.SearchBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchManager  {
    public static List<SearchBean> getSearch(Document document){
        List<SearchBean> searchBean=new ArrayList<>();
        Elements element=document.select("div.resultlist").select("div.item");
        if(element!=null&&element.size()>0){
            for(Element item:element){
                String title=item.select("h3").first().select("a").html();
                String href=item.select("h3").first().select("a").attr("href");
                String intro=item.select("p.intro").first().html();
                String info=item.select("p.info").first().text();
                SearchBean search=new SearchBean();
                search.setTitle(title);
                search.setHref(href);
                search.setIntro(intro);
                search.setType(info);
                searchBean.add(search);
            }
        }
        return searchBean;
    }
}
