package com.sqq.po;

/**
 * @description: TokenController中的请求参数POJO
 * @author: shiqiangqiang
 * @createDate: 2020/3/21
 * @version: 1.0
 */
public class TokenParam {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TokenParam{" +
                "userId='" + userId + '\'' +
                '}';
    }
}