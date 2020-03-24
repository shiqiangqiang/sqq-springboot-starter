package com.sqq.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @description: POJO自定义父类
 * @author: shiqiangqiang
 * @createDate: 2020/3/24
 * @version: 1.0
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public BaseModel() {

    }

    @Override
    public String toString(){
        /**
         * 参考博客： https://blog.csdn.net/wangmaohong0717/article/details/54893771
         * 子类都会反射自己的字段并输出如：Xxx[aa=,bb=,cc=]之类的
         */
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}