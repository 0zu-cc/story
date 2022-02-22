package com.storyxc.service.impl;

import com.github.pagehelper.PageInfo;
import com.storyxc.mapper.ImageDao;
import com.storyxc.pojo.Image;
import com.storyxc.pojo.QueryPageBean;
import com.storyxc.service.ImageService;
import com.storyxc.util.PageHelperUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Xc
 * @description
 * @createdTime 2020/1/14 18:35
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void saveImage(Image image) {
        String imageDate = image.getEnddate();
        String date = imageDate.substring(0, 4) + "-" + (imageDate.substring(4, 6)) + "-" + (imageDate.substring(6, 8));
        image.setDate(date);
        String fullName = image.getUrl().substring("/th?id=OHR.".length(), image.getUrl().indexOf(".jpg") + 4);
        image.setFullName(fullName);
        image.setFullUrl("http://io.storyxc.com/images/" + fullName);
        imageDao.saveImage(image);
    }

    @Override
    public PageInfo<Image> findPage(QueryPageBean queryPageBean) {
        PageHelperUtils.startPage(queryPageBean);
        List<Image> imageList = imageDao.findPage(queryPageBean.getQueryString());
        return new PageInfo<>(imageList);
    }

    @Override
    public Image getImageByName(String fullName) {
        return imageDao.getImageByName(fullName);
    }

    @Override
    public Image getImageByDate(String flag) {
        return imageDao.getImageByDate(flag);
    }

    @Override
    public void updateDownloadCount(String flag) {
        imageDao.updateDownloadCount(flag);
    }

    @Override
    public Integer updateLikeCount(String date, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "like_" + date)) {
                    return null;
                }
            }
        }
        Cookie cookie = new Cookie("like_" + date, "1");
        cookie.setMaxAge(600);
        cookie.setPath("/");
        response.addCookie(cookie);
        imageDao.updateLikeCount(date);
        return imageDao.getLikeCountByDate(date);
    }

    @Override
    public Integer getDownloadCount(String flag) {
        return imageDao.getDownloadCount(flag);
    }

    @Override
    public Image getTodayPic() {
        return imageDao.getTodayPic();
    }

    @Override
    public void pull() throws Exception {
        String templateUrl = "https://bing.ioliu.cn/?p=";
        Integer index = 1;
        boolean flag = false;
        while (true) {
            Document doc = Jsoup.connect(templateUrl + index++).get();
            Elements items = doc.select(".item");
            if (items != null) {
                for (Element item : items) {
                    String description = item.select("div.description > h3").text();
                    String date = item.select("div.description > p").text().split(" ")[0];
                    if (date.equals("2022-02-17")) {
                        flag = true;
                        break;
                    }
                    String url = "/th?id=OHR." + (item.select("a.mark").attr("href").substring(6).split("\\?")[0] + "_1920x1080.jpg").substring(1);
                    String fullUrl = "https://io.storyxc.com/images" + item.select("a.mark").attr("href").substring(6).split("\\?")[0] + "_1920x1080.jpg";

                    Image image = new Image();
                    image.setFullUrl(fullUrl);
                    image.setCopyright(description);
                    image.setDate(date);
                    image.setUrl(url);
                    imageDao.saveImage(image);
                }
                if (flag) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String templateUrl = "https://bing.ioliu.cn/?p=";

        Integer index = 1;


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean flag = false;
        while (true) {
            String s = templateUrl + index++;
            Document doc = Jsoup.connect(s).get();
            Elements items = doc.select(".item");
            String date = null;
            if (items != null) {
                for (Element item : items) {
                    String description = item.select("div.description > h3").text();
                    date = item.select("div.description > p").text().split(" ")[0];
                    String url = "/th?id=OHR." + (item.select("a.mark").attr("href").substring(6).split("\\?")[0] + "_1920x1080.jpg").substring(1);
                    String fullUrl = "https://io.storyxc.com/images" + item.select("a.mark").attr("href").substring(6).split("\\?")[0] + "_1920x1080.jpg";

                    if (date.equals("2022-12-22")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
            }


            break;
        }
    }
}
}
