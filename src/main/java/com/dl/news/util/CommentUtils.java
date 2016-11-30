package com.dl.news.util;

/**
 * Created by Administrator on 2016/11/2.
 */
public interface CommentUtils {
    String APP_ID = "26470";
    String SHOWAPI_SIGN = "b24c660849fd42be80b7ae51e55d530d";
    String URL_SOCIETY_VP = "http://192.168.1.115:8080/pics.json";
    String URL_SOCIETY_NEWS = "http://route.showapi.com/109-35?showapi_appid=" +
            APP_ID + "&showapi_sign=" + SHOWAPI_SIGN;
    String URL_SOCIETY_NEWS_ID = "http://route.showapi.com/109-34?showapi_appid=" +
            APP_ID + "&showapi_sign=" + SHOWAPI_SIGN;
    String URL_HOT_NEWS = "http://route.showapi.com/109-35?showapi_appid=26470&showapi_sign=b24c660849fd42be80b7ae51e55d530d&needContent=0&needHtml=0&needAllList=0";
    String URL_HOT_WOMAN = "http://route.showapi.com/197-1?showapi_appid=26895&showapi_sign=d0e2ce26e11c412db6aca77b5449ec0f";
    String URL_HOT_DONGMAN ="http://route.showapi.com/959-1?showapi_appid=26895&type=dmbz&showapi_sign=d0e2ce26e11c412db6aca77b5449ec0f";
    String URL_HOT_DONGMAN1 ="http://route.showapi.com/959-2?showapi_appid=26895&showapi_sign=d0e2ce26e11c412db6aca77b5449ec0f&id=";
}
