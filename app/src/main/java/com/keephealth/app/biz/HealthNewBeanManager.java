package com.keephealth.app.biz;

import com.keephealth.app.entity.HealthNewBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/6/19.
 * author:秦浩雄
 * 作用:首页的轮播栏和养生要闻管理器
 */

public class HealthNewBeanManager {
    static String[] imgTitleUrl = {"/html/2017/0516/6786.html", "/html/2017/0516/6785.html", "/html/2017/0418/6783.html", "/html/20170418/6782.html", "/html/2017/0321/6780.shtml"};
    static String[] imgTitle = {"非糖尿病也会发生低血糖", "入夏起居饮食情志 养生从心开始", "正确吃饭是治疗糖尿病的关键环节", "春食野菜的诱惑 有诱惑也有陷阱", "国家老龄事业发展和养老体系建设规划"};
    static String[] imgUrl = {"/uploads/allimg/170516/1_170516121021_1-lp.jpg", "/uploads/allimg/170516/1_170516120526_1-lp.jpg",
            "/uploads/allimg/170418/1_170418102529_1-lp.jpg", "/uploads/allimg/170418/1_170418102240_1-lp.jpg",
            "/uploads/allimg/170321/1_170321153152_1-lp.jpg"};

    public static List<HealthNewBean> getImgList(Document document,String result) {
        List<HealthNewBean> categoriesBeans = new ArrayList();
//        Element element = document.select("div.wrap").get(4).select("div.hot375").first().select("div#videos").first();
//        Element element1 = element.select("div.v").first().select("div.pics").first().select("div#container").first().select("div#example").first();
//        Elements element3 = element1.select("div#example").first().select("div#slides").first().select("div.slides_container").first().select("div");
//        Log.d("sds", element3 + "");
//        for(Element es:element3){
//            HealthNewBean bean = new HealthNewBean();
//            String imgTitleUrl = es.select("a").attr("href");
//            String imgUrl = es.select("a").first().select("img").attr("src");
//            String imgTitle = es.select("div").select("div.caption").first().text();
//            bean.setImgTitle(imgTitle);
//            bean.setImgTitleUrl(imgTitleUrl);
//            bean.setImgUrl(imgUrl);
//            categoriesBeans.add(bean);
//        }
        for (int i = 0; i < 5; i++) {
            HealthNewBean bean = new HealthNewBean();
            String imgTitleUrls = imgTitleUrl[i];
            String imgUrls =imgUrl[i];
            String imgTitles = imgTitle[i];
            bean.setImgTitleUrl(imgTitleUrls);
            bean.setImgTitle(imgTitles);
            bean.setImgUrl(imgUrls);
            categoriesBeans.add(bean);
        }
        return categoriesBeans;
    }

    public static List<HealthNewBean> getTitleList(Document document) {
        List<HealthNewBean> categoriesBeans = new ArrayList();
        Element element = document.select("div.wrap").get(4).select("div.hot373").first().select("div.hotcnt").first();
        if(element==null){
            return categoriesBeans;
        }
        Element element1 = element.select("div.c").first();
        if(element1==null){
            return categoriesBeans;
        }
        Elements element2 = element1.select("ul").select("li");
        if(element2==null){
            return categoriesBeans;
        }
        for (Element el : element2) {
            HealthNewBean beans = new HealthNewBean();
            String titleUrl1 = el.select("a").first().attr("href");
            String title1 = el.select("a").first().text();
            String maskUrl = el.select("a").last().attr("href");
            String mask = el.select("a").last().text();
            beans.setTitle(title1);
            beans.setTitleUrl(titleUrl1);
            beans.setMask(mask);
            beans.setMaskUrl(maskUrl);
            categoriesBeans.add(beans);
        }
        return categoriesBeans;
    }
}
