package com.imooc.ad.index;

/**
 * @author chenqiang
 * @create 2020-06-15 9:49
 */
public interface IndexAware<K,V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
