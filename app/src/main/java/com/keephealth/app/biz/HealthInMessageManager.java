package com.keephealth.app.biz;


import com.keephealth.app.common.Config;
import com.keephealth.app.entity.HealthMessage;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HealthInMessageManager {
    public HealthInMessageManager() {
    }

    public static List<HealthMessage> getlist(Document document) {
        List<HealthMessage> healthBean = new ArrayList<>();
        Element element1 = document.select("div.w960").last();
        if (element1 != null) {
            Element element = element1.select("div.pleft").first();
            Element elements = element.select("div.listbox").first();
            Elements getlist = elements.select("ul.e2").select("li");
            for (Element list : getlist) {
                String imgurl = list.select("a").first().select("img").attr("src");
                String href = list.select("a").last().attr("href");
                String title = list.select("a").last().text();
                String date = list.select("span.info").html();
                String intro = list.select("p.intro").text();
                HealthMessage message = new HealthMessage();
                message.setDate(date);
                message.setImgurl(Config.CRAWLER_URL + imgurl);
                message.setHref(href);
                message.setTitle(title);
                message.setIntro(intro);
                healthBean.add(message);
            }
        }
        return healthBean;
    }
}