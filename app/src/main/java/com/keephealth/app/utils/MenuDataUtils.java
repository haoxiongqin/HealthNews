package com.keephealth.app.utils;


import com.keephealth.app.R;
import com.keephealth.app.entity.LeftItemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前类注释:左侧菜单Item数据构造
 */
public class MenuDataUtils {
    public static List<LeftItemMenu> getItemMenus(){
        List<LeftItemMenu> menus=new ArrayList<LeftItemMenu>();
        menus.add(new LeftItemMenu(R.drawable.icon_zhanghaoxinxi,"心理养生"));
        menus.add(new LeftItemMenu(R.drawable.icon_zhanghaoxinxi,"养生要闻"));
        menus.add(new LeftItemMenu(R.drawable.icon_wodeguanzhu,"中医养生之道"));
        menus.add(new LeftItemMenu(R.drawable.icon_shoucang,"养生食谱"));
        menus.add(new LeftItemMenu(R.drawable.icon_shezhi,"精选视频"));
        return  menus;
    }
}
