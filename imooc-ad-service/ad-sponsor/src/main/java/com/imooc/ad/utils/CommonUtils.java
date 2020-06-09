package com.imooc.ad.utils;

import com.imooc.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author chenqiang
 * @create 2020-06-09 15:10
 */
public class CommonUtils {

    public static String[] parsePatterns = {
            "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"
    };

    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String stringDate) throws AdException {
        try {
            return DateUtils.parseDate(stringDate,parsePatterns);
        } catch (Exception e) {
            throw new AdException(e.getMessage());
        }
    }
}
