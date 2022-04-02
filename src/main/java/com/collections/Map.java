package com.collections;

public interface Map<K, V> {
    int size();
    V put(K key, V value);
    boolean contains(K key);
    V get(K key);
    V remove(K key);
}
