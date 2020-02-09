package com.storyxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.storyxc.service.DouyuStreamService;
import com.storyxc.util.MD5Util;
import com.storyxc.util.http.HttpContentType;
import com.storyxc.util.http.HttpRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xc
 * @description
 * @createdTime 2020/2/9 17:20
 */
@Service
public class DouyuStreamServiceImpl implements DouyuStreamService {

    private static final Pattern PATTERN = Pattern.compile("(function ub9.*)[\\s\\S](var.*)");
    private static final Pattern PATTERN2 = Pattern.compile("(?<=/live/).*(?=/playlist)");
    private static final Pattern PATTERN3 = Pattern.compile("^[0-9a-zA-Z]*");

    public static String getTimeStr(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    private static JSONObject get_tt() {
        long nowTime = System.currentTimeMillis();
        String tt1 = String.valueOf(nowTime / 1000);
        String tt2 = String.valueOf(nowTime);
        String today = getTimeStr(nowTime, "yyyyMMdd");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tt1", tt1);
        jsonObject.put("tt2", tt2);
        jsonObject.put("today", today);
        return jsonObject;
    }

    private static JSONObject get_homejs(String rid) {
        String room_url = "https://m.douyu.com/" + rid;
        String response = HttpRequest.create(room_url).get().getBody();
        String real_rid = response.substring(response.indexOf("{\"rid\":") + "{\"rid\":".length());
        real_rid = real_rid.substring(0, real_rid.indexOf(","));

        if (!rid.equals(real_rid)) {
            room_url = "https://m.douyu.com/" + real_rid;
            response = HttpRequest.create(room_url).get().getBody();
        }

        Matcher matcher = PATTERN.matcher(response);
        if (!matcher.find()) {
            return null;
        }

        String result[] = matcher.group().split("\n");
        String str1 = result[0].replaceAll("eval.*;", "strc;");
        String homejs = str1 + result[1];

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("homejs", homejs);
        jsonObject.put("real_rid", real_rid);
        return jsonObject;
    }

    private static String get_sign(String rid, String post_v, String tt, String ub9) throws ScriptException, NoSuchAlgorithmException {
        ub9 += "ub98484234();";
        ScriptEngine docjs = new ScriptEngineManager().getEngineByName("javascript");
        String res2 = docjs.eval(ub9).toString();
        String str3 = res2.replaceAll("\\(function[\\s\\S]*toString\\(\\)", "\'");
        String md5rb = MD5Util.md5String(rid + "10000000000000000000000000001501" + tt + "2501" + post_v);
        String str4 = "function get_sign(){var rb=\'" + md5rb + str3;
        String str5 = str4.replaceAll("return rt;}[\\s\\S]*", "return re;};");
        String str6 = str5.replaceAll("\"v=.*&sign=\"\\+", "");
        str6 += "get_sign(" + rid + ",\"10000000000000000000000000001501\",\"" + tt + "\")";
        String sign = docjs.eval(str6).toString();

        return sign;
    }

    private static String mix_room(String rid) {
        return "PKing";
    }

    private static String get_pre_url(String rid, String tt) throws NoSuchAlgorithmException {
        String request_url = "https://playweb.douyucdn.cn/lapi/live/hlsH5Preview/" + rid;

        String auth = MD5Util.md5String(rid + tt);

        JSONObject response = HttpRequest.create(request_url)
                .setContentType(HttpContentType.FORM)
                .putHeader("rid", rid)
                .putHeader("time", tt)
                .putHeader("auth", auth)
                .appendParameter("rid",rid)
                .appendParameter("did","10000000000000000000000000001501")
                .post()
                .getBodyJson();

        String pre_url = "";
        if (response.getIntValue("error") == 0) {
            String real_url = (response.getJSONObject("data")).getString("rtmp_live");
            if (real_url.contains("mix=1")) {
                pre_url = mix_room(rid);
            } else {
                Matcher matcher = PATTERN3.matcher(real_url);
                if (!matcher.find()) {
                    return null;
                }
                pre_url = matcher.group();
            }
        }

        return pre_url;
    }

    private static String get_sign_url(String post_v, String rid, String tt, String ub9) throws ScriptException, NoSuchAlgorithmException {
        String sign = get_sign(rid, post_v, tt, ub9);
        String request_url = "https://m.douyu.com/api/room/ratestream";

        JSONObject response = HttpRequest.create(request_url)
                .setContentType(HttpContentType.FORM)
                .putHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Mobile Safari/537.36")
                .appendParameter("v", "2501" + post_v)
                .appendParameter("did", "10000000000000000000000000001501")
                .appendParameter("tt", tt)
                .appendParameter("sign", sign)
                .appendParameter("ver", "219032101")
                .appendParameter("rid", rid)
                .appendParameter("rate", "-1")
                .post()
                .getBodyJson();

        if (response.getIntValue("code") != 0) {
            return null;
        }
        String real_url = (response.getJSONObject("data")).getString("url");
        if (real_url.contains("mix=1")) {
            return mix_room(rid);
        } else {
            Matcher matcher = PATTERN2.matcher(real_url);
            if (!matcher.find()) {
                return null;
            }
            return matcher.group();
        }
    }

    private static String get_real_url(String rid) throws ScriptException, NoSuchAlgorithmException {
        JSONObject tt = get_tt();
        String today = tt.getString("today");
        String tt1 = tt.getString("tt1");
        String tt2 = tt.getString("tt2");
        String url = get_pre_url(rid, tt2);
        if (StringUtils.isNotBlank(url)) {
            return "http://tx2play1.douyucdn.cn/live/" + url + ".flv";
        } else {
            JSONObject result = get_homejs(rid);
            String real_rid = result.getString("real_rid");
            String homejs = result.getString("homejs");
            String real_url = get_sign_url(today, real_rid, tt1, homejs);
            if (StringUtils.isBlank(real_url)) {
                return "未开播";
            }
            return "http://tx2play1.douyucdn.cn/live/" + real_url + ".flv";
        }
    }


    @Override
    public String getRealLiveStreamURL(String roomId) {
        String realLiveStreamURL = null;
        try {
            realLiveStreamURL = get_real_url(roomId);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return realLiveStreamURL;
    }
}
