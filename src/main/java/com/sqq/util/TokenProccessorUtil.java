package com.sqq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @description: Token工具类(单例)
 * @author: shiqiangqiang
 * @createDate: 2020/3/21
 * @version: 1.0
 */
public class TokenProccessorUtil {
    private static final Logger log = LoggerFactory.getLogger(TokenProccessorUtil.class);

    private TokenProccessorUtil(){}

    private static final TokenProccessorUtil tokenProcessor = new TokenProccessorUtil();

    private Random random = new Random();

    /**
     * 获取token工具对象
     * @return
     */
    public static TokenProccessorUtil getInstance(){
        return tokenProcessor;
    }

    /**
     * 生产并返回一个token
     * @return
     */
    public String produceToken(){
        String token= "";
        String tokenFormat = System.currentTimeMillis() + random.nextInt(999999999) + "";
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] md5 = messageDigest.digest(tokenFormat.getBytes());
            //base64编码--任意二进制编码明文字符
            BASE64Encoder encoder = new BASE64Encoder();
            token = encoder.encode(md5);

        } catch (NoSuchAlgorithmException e) {
            log.error("TokenProccessorUtil produceToken, has error! ex:" + e);
        }
        return token;
    }


}