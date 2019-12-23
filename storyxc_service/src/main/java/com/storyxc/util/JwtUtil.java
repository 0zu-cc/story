package com.storyxc.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 15:01
 */
public class JwtUtil implements Serializable {

    public static final String AUTHORIZATION = "Authorization";

    public static final String AUTH_BEGIN = "Bearer ";

    //有效期为1小时
    public static final Long JWT_TTL = 3600000L;

    //Jwt令牌信息
    public static final String JWT_KEY = "storyxc";

    public static String createJWT(String id, String subject, Long ttlMillis) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);

        //如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成秘钥
        SecretKey secretKey = generalKey();

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                .setId(id)                    //唯一的ID
                .setSubject(subject)          // 主题  可以是JSON数据
                .setIssuer("admin")          // 签发者
                .setIssuedAt(now)             // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
                .setExpiration(expDate);      // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析令牌数据
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 校验令牌
     * @param username
     * @param token
     * @return
     */
    public static boolean validateToken(String username, String token) throws Exception {
        String user = parseJWT(token).getSubject();
        return (username.equals(user) && isTokenAvailable(token));
    }

    private static boolean isTokenAvailable(String token) throws Exception {
        Date expiration = parseJWT(token).getExpiration();
        return expiration.after(new Date());
    }
}
