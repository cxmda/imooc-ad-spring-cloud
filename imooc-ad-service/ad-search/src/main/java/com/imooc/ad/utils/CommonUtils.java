package com.imooc.ad.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author chenqiang
 * @create 2020-06-15 10:05
 */
@Slf4j
public class CommonUtils {

    public static <K,V> V getorCreate(K key, Map<K,V> map, Supplier<V> factory){
        return map.computeIfAbsent(key,k -> factory.get());
    }
}
