package com.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SinglyLinkedList<T> implements Stack<T>, Queue<T> {
    private static class Node<V> {
        V value;
        Node<V> next;

        public Node() {
            next = this;
        }

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    final Node<T> head = new Node<>();
    Node<T> tail = head;
    int size = 0;

    @Override
    public void reverse() {
        if (size == 0) {
            return;
        }
        Node<T> cursor = head.next.next;
        Node<T> newChain = head.next;
        newChain.next = head;
        tail = newChain;
        while (cursor != head) {
            var cur = cursor;
            cursor = cursor.next;
            cur.next = newChain;
            newChain = cur;
        }
        head.next = newChain;
    }

    @Override
    public String toString() {
        var res  = new StringJoiner(", ", "[", "]");
        for (var el : this) {
            res.add(el.toString());
        }
        return res.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if (size != 0) {
            head.next = head;
            tail = head;
            size = 0;
        }
    }

    @Override
    public boolean push(T element) {
        head.next = new Node<>(element, head.next);
        size++;
        return true;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty");
        }
        var firstNode = head.next;
        head.next = firstNode.next;
        size--;
        if (size == 0) {
            tail = head;
        }
        return firstNode.value;
    }

    @Override
    public boolean enqueue(T element) {
        tail = tail.next = new Node<>(element, head);
        size++;
        return true;
    }

    @Override
    public T dequeue() {
        return pop();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty");
        }
        return head.next.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new HeadToTailIterator();
    }

    private class HeadToTailIterator implements Iterator<T> {
        Node<T> cursor = head;
        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = cursor.next;
            return cursor.value;
        }

        @Override
        public void remove() {
            // TOD
        }
    }

    public static void main(String[] args) {
        Queue<String> col = new SinglyLinkedList<>();
        col.enqueue("#1");
        col.enqueue("#2");
        col.enqueue("#3");
        col.enqueue("#4");
        // System.out.println(col);

        col.reverse();

        assert col.toString().equals("[#4, #32, #2, #1]") : "Error message";
    }
}
