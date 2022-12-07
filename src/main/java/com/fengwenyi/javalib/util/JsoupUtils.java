package com.fengwenyi.javalib.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Jsoup 工具类
 *
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-12-07
 */
public class JsoupUtils {

    /**
     * 从 html 内容中解析标题
     * @param html html内容
     * @return 标题
     */
    public static String parseTitle(String html) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("title");
        Element element = elements.get(0);
        return element.text();
    }

}
