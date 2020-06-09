package com.imooc.ad.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author chenqiang
 * @create 2020-06-09 15:10
 */
public class CommonUtils {

    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }
}
