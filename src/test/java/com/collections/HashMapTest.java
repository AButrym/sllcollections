package com.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    Map<String, String> map = new HashMap<>();

    @Test
    @DisplayName("Empty map has size 0")
    void name01() {
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("after put we can get the put value")
    void name02() {
        map.put("key1", "val1");
        assertEquals("val1", map.get("key1"));
    }
}