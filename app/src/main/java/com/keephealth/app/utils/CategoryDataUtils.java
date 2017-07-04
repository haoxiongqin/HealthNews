package com.keephealth.app.utils;


import com.keephealth.app.entity.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:
 */
public class CategoryDataUtils {
    public static List<CategoriesBean>  getCategoryBeans(){
        List<CategoriesBean>  beans=new ArrayList<>();
        beans.add(new CategoriesBean("健康资讯","http://www.cpoha.com.cn/mynews/jiankang/","资讯"));
        beans.add(new CategoriesBean("大家养生","http://www.cpoha.com.cn/yangsheng/mingjiayangsheng/","大家养生"));
        beans.add(new CategoriesBean("膳食养生","http://www.cpoha.com.cn/shanshi/shanshi/yangshengshipu/","膳食养生"));
        beans.add(new CategoriesBean("药膳养生","http://www.cpoha.com.cn/zhongyi/zhongyaoyaoshan/","药膳养生"));
        beans.add(new CategoriesBean("养生动态","http://www.cpoha.com.cn/news/","养生动态"));
        return beans;
    }
}
