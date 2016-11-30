package com.dl.news.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2.
 */
public class SocietyPager {

    /**
     * imgurl : http://192.168.1.110:8080/img/a.jpg
     * title : 超级周来临 汇市或剧烈波动
     */

    public List<ImagesBean> images;

    public static class ImagesBean {
        public String imgurl;
        public String title;
    }
}
