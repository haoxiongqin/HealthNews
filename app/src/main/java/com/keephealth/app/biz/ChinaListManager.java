package com.keephealth.app.biz;

import com.keephealth.app.entity.ChinaBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/6/27.
 * author:悟.静
 * 作用:
 */

public class ChinaListManager  {
    public ChinaListManager(){
    }
    public List<ChinaBean> getChinese(Document document){
        List<ChinaBean> categoriesBeans=new ArrayList();
        Element element1=document.select("div#pageMain").first().select("div#archivesList").first();
        if(element1==null){
            return categoriesBeans;
        }
        Elements element=element1.select("ol").select("li");
        if(element==null){
            return categoriesBeans;
        }
        for (Element elements : element) {
            String title=elements.select("cite").first().select("a").text();
            String date=elements.select("span").text();
            String href=elements.select("cite").first().select("a").attr("abs:href");
            ChinaBean bean=new ChinaBean();
            bean.setTitle(title);
            bean.setDate(date);
            bean.setTitleUrl(href);
            categoriesBeans.add(bean);
        }
        return categoriesBeans;
    }
}
