package com.keephealth.app.common;

import com.keephealth.app.entity.TagBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目数据常量配置
 * @author jiangqq
 *
 */
public class Config {
  public static final String CRAWLER_URL="http://www.cpoha.com.cn";
  public static final String LZYYSW="http://www.lzyysw.com";
  public static final String YANGSHENGWANG="http://www.6681.com";
  public static final String XLY="http://www.6681.com/xinliyangsheng/";
  public static final String REMARK="REMARK";

  public List<TagBean> jkList;
  public List<TagBean> lifeList;
  public List<TagBean> studyList;
  public List<TagBean> testList;
  public List<TagBean> constelList;
  public List<TagBean> animalsList;
  public List<TagBean> geomancyList;

  public List<TagBean> getStudyList() {
    studyList=new ArrayList();
    for(int i=0;i<Study.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(StudyUrl[i]);
      tagBean.setTagname(Study[i]);
      jkList.add(tagBean);
    }
    return studyList;
  }

  public List getJkList() {
    jkList=new ArrayList();
    for(int i=0;i<mentalJK.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(mentalJKUrl[i]);
      tagBean.setTagname(mentalJK[i]);
      jkList.add(tagBean);
    }
    return jkList;
  }

  public List getLifeList() {
    lifeList=new ArrayList();
    for(int i=0;i<Life.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(LifeUrl[i]);
      tagBean.setTagname(Life[i]);
      jkList.add(tagBean);
    }
    return lifeList;
  }

  public List getTestList() {
    testList=new ArrayList();
    for(int i=0;i<Test.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(TestUrl[i]);
      tagBean.setTagname(Test[i]);
      jkList.add(tagBean);
    }
    return testList;
  }

  public List getConstelList() {
    constelList=new ArrayList();
    for(int i=0;i<Constel.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(ConstelUrl[i]);
      tagBean.setTagname(Constel[i]);
      jkList.add(tagBean);
    }
    return constelList;
  }

  public List getAnimalsList() {
    animalsList=new ArrayList();
    for(int i=0;i<Animals.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(AnimalsUrl[i]);
      tagBean.setTagname(Animals[i]);
      jkList.add(tagBean);
    }
    return animalsList;
  }

  public List getGeomancyList() {
    geomancyList=new ArrayList();
    for(int i=0;i<Geomancy.length;i++){
      TagBean tagBean=new TagBean();
      tagBean.setHref(GeomancyUrl[i]);
      tagBean.setTagname(Geomancy[i]);
      jkList.add(tagBean);
    }
    return geomancyList;
  }

  public String[] tytitle = new String[]{"心理健康", "心理学与生活", "心理测试", "心理学", "星座查询", "生肖配对", "风水学"};
  public String[] titleUlr1 = new String[]{XLY+"xinlijb/",XLY+"shenghuo/",XLY+"ceshi/",XLY+"xinlixue/",
          XLY+"xingzuo/",XLY+"shengxiao/",XLY+"fengshui/", XLY+"huangdaojiri/"};

  public  String []mentalJK={"恐惧症","强迫症","自闭症","焦虑症","多动症","失语症","躁狂症","妄想症","恐高症","社交恐惧症","密集恐惧症"};
  public String []mentalJKUrl={XLY+"kongjuzheng/",XLY+"qpz/",XLY+"zbz/",XLY+"jlz/",
          XLY+"ddz/",XLY+"xinlijb/syz/",XLY+"xinlijb/zkz/",XLY+"xinlijb/wxz/",XLY+"xinlijb/kgz/",
          XLY+"xinlijb/sjkjz/",XLY+"xinlijb/mjkjz/"};

  public String []Life={"职场法则","人际交往","家庭生活","自我认知","心灵鸡汤","成长心理","健康减压"};
  public String []LifeUrl={XLY+"zhichang/",XLY+"rjjw/",XLY+"jtsh/",XLY+"zwrz/",
          XLY+"xljt/",XLY+"chengchangxinli/",XLY+"jkjy/"};

  public String []Test={"爱情测试","性格测试","事业测试","趣味测试","其他测试","财富测试","智商测试","社交测试"};
  public String []TestUrl={XLY+"aqcs/",XLY+"xgcs/",XLY+"sycs/",XLY+"qwcs/",
          XLY+"qita/",XLY+"caifuceshi/",XLY+"zhishangceshi/",XLY+"shejiaoceshi/",};

  public String []Study={"色彩心理学","微表情","儿童心理学","生活心理学","犯罪心理学","行为心理学","社会心理学","人格心理学","恋爱心理学","销售心理学"};
  public String []StudyUrl={XLY+"secai/",XLY+"wbq/",XLY+"ertong/",XLY+"xinlixue/shenghuo/",
          XLY+"xinlixue/fzxlx/",XLY+"xinlixue/xwxlx/",XLY+"xinlixue/shxlx/",XLY+"xinlixue/rgxlx/",YANGSHENGWANG+"/xinlixue/laxlx/",YANGSHENGWANG+"/xinlixue/xsxlx/"};

  public String []Constel={"天蝎座","双鱼座","狮子座","摩羯座","天秤座","双子座","白羊座","金牛座","巨蟹座","处女座","水瓶座","射手座"};
  public String []ConstelUrl={XLY+"tianxiezuo/",XLY+"shuangyuzuo/",XLY+"shizizuo/",XLY+"mojiezuo/",XLY+"tianchengzuo/",YANGSHENGWANG+"/shuangzizuo/"
          ,XLY+"baiyangzuo/",XLY+"jinniuzuo/",XLY+"juxiezuo/",XLY+"chunvzuo/",XLY+"shuipingzuo/",XLY+"sheshouzuo/"};

  public String []Animals={"属鼠","属牛","属虎","属龙","属蛇","属马","属羊","属猴","属鸡","属狗","属兔","属猪"};
  public String []AnimalsUrl={XLY+"shengxiao/shu/",XLY+"shengxiao/niu/",XLY+"shengxiao/hu/",XLY+"shengxiao/long/",XLY+"shengxiao/she/",XLY+"/shengxiao/ma/"
          ,XLY+"shengxiao/yang/",XLY+"shengxiao/hou/",XLY+"shengxiao/ji/",XLY+"shengxiao/gou/",XLY+"shengxiao/tu/",XLY+"shengxiao/zhu/"};

  public String []Geomancy={"解梦","风水学","黄道吉日","血型","塔罗牌","面相学","手相学"};
  public String []GeomancyUrl={XLY+"jiemeng/",XLY+"fengshui/",XLY+"huangdaojiri/",XLY+"xxml/xuexing/",XLY+"taluopai",XLY+"mianxiangxue/"
          ,XLY+"shouxiangxue/"};
}
