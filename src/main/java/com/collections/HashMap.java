package com.collections;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.75f;

    private static class Node<K1, V1> {
        final int hash;
        final K1 key;
        V1 value;
        Node<K1, V1> next;

        public Node(K1 key, V1 value) {
            hash = Objects.hash(key);
            this.key = key;
            this.value = value;
        }
    }

    int size = 0;
    Node<K, V>[] bins = new Node[INITIAL_CAPACITY];

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K key, V value) {
        int bin = binNumber(key);
        var node = find(key, bin);
        if (node != null) {
            var res = node.value;
            node.value = value;
            return res;
        } else {
            var newNode = new Node<K, V>(key, value);
            if (bins[bin] == null) {
                bins[bin] = newNode;
            } else {
                newNode.next = bins[bin];
                bins[bin] = newNode;
            }
            incrementSize();
            return null;
        }
    }

    private void incrementSize() {
        size++;
        if (size > LOAD_FACTOR * bins.length) {
            var oldBins = bins;
            bins = new Node[2 * bins.length];
            // todo: resettle all the nodes from oldBins to bins using
        }
    }

    @Override
    public V remove(K key) {
        var node = find(key, binNumber(key));
        if (node == null) {
            return null;
        } else {
            // todo: find the previous node and reconnect it with the next
            size--;
            return node.value;
        }
    }

    private Node<K, V> find(K key, int bin) {
        int hash = Objects.hash(key);
        var node = bins[bin];
        while (node != null) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private int binNumber(K key) {
        return Objects.hash(key) % bins.length;
    }

    @Override
    public boolean contains(K key) {
        return find(key, binNumber(key)) != null;
    }

    @Override
    public V get(K key) {
        var node = find(key, binNumber(key));
        return node != null ? node.value : null;
    }
}
