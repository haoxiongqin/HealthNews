package com.keephealth.app.biz;

import com.keephealth.app.entity.MySection;
import com.keephealth.app.entity.VideoBaseBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2017/6/29.
 * author:悟.静
 * 作用:
 */

public class VideoManager {
    public static List<VideoBaseBean> getBanerList(Document document){
        List<VideoBaseBean> baseBeen=new ArrayList<>();
        Element element=document.select("div.all").first().select("div.shipin_ys1").first().select("div.shipin_ys1_left").first();
        Element element1=element.select("div#zSlider").first().select("div#picshow").first().select("div#picshow_img").first();
        if(element1==null){
            return null;
        }
        Elements elements=element1.select("ul").first().select("li");
        if(elements==null){
            return baseBeen;
        }
        for(Element el:elements){
            String title=el.select("a").attr("title");
            String titleUrl=el.select("a").attr("href");
            String img=el.select("a").select("img").attr("abs:src");
            VideoBaseBean bean=new VideoBaseBean();
            bean.setTitle(title);
            bean.setImgUrl(img);
            bean.setTitleUrl(titleUrl);
            baseBeen.add(bean);
        }
        return baseBeen;
    }

    public static List<MySection>  getList(Document document){
        List<MySection> list = new ArrayList<>();
        Element element1=document.select("div.all").first();
        setReturn(element1);
        //贵州卫视养生
        Element element22=element1.select("div.shiping_ys3").get(1);
        setReturn(element22);
        Element element23=element22.select("div.tit1000").first();
        setReturn(element23);
        String moreUrl2=element23.select("i").first().select("a").attr("abs:href");
        Elements element24=element22.select("div.dow").first().select("ul").first().select("li");
        list.add(new MySection(true, "贵州卫视养生", true,moreUrl2));
        for(Element el:element24){
            String title=el.select("a").select("p").text();
            String titleUrl=el.select("a").attr("href");
            String img=el.select("a").select("img").attr("abs:src");
            list.add(new MySection(new VideoBaseBean(title,img,titleUrl,"")));
        }
        //养生堂
        Element element32=element1.select("div.shiping_ys3").get(2);
        setReturn(element32);
        Element element33=element32.select("div.tit1000").first();
        setReturn(element33);
        String moreUrl3=element33.select("i").first().select("a").attr("abs:href");
        Elements element34=element32.select("div.dow").first().select("ul").first().select("li");
        list.add(new MySection(true, "养生堂", true,moreUrl3));
        for(Element el:element34){
            String title=el.select("a").select("p").text();
            String titleUrl=el.select("a").attr("href");
            String img=el.select("a").select("img").attr("abs:src");
            list.add(new MySection(new VideoBaseBean(title,img,titleUrl,"")));
        }
        //养生一点通
        Element element2=element1.select("div.shiping_ys3").get(3);
        setReturn(element2);
        Element element3=element2.select("div.tit1000").first();
        setReturn(element3);
        String moreUrl=element3.select("i").first().select("a").attr("abs:href");
        Elements element4=element2.select("div.dow").first().select("ul").first().select("li");
        list.add(new MySection(true, "养生一点通", true,moreUrl));
        for(Element el:element4){
            String title=el.select("a").select("p").text();
            String titleUrl=el.select("a").attr("href");
            String img=el.select("a").select("img").attr("abs:src");
            list.add(new MySection(new VideoBaseBean(title,img,titleUrl,"")));
        }
        //猜你喜欢
        Element element42=element1.select("div.sp_qiehuang").first().select("div.sp_tabs_container").first();
        setReturn(element42);
        Elements element44=element42.select("ul.ui-tabs-panel").get(0).select("li");
        list.add(new MySection(true, "猜你喜欢", false,""));
        for(Element el:element44){
            String title=el.select("a").select("p").text();
            String titleUrl=el.select("a").attr("href");
            String img=el.select("a").select("img").attr("abs:src");
            list.add(new MySection(new VideoBaseBean(title,img,titleUrl,"")));
        }
        return list;
    }
    public static void setReturn(Element element){
        if(element==null){
            return;
        }
    }
}
