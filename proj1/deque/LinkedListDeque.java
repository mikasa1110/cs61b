package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, deque.Deque<T> {

    private static class Node<T> {
        private T val;
        private Node<T> next;
        private Node<T> prev;

        Node(T val, Node<T> next, Node<T> prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedListDeque() {
        head = new Node<T>(null, null, null);
        tail = new Node<T>(null, null, null);
        head.next = tail;
        head.prev = tail;
        tail.prev = head;
        tail.next = head;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> tmp = this.head.next;
        Node<T> node = new Node<>(item, tmp, this.head);
        this.head.next = node;
        tmp.prev = node;
        this.size++;
    }

    public void addLast(T item) {
        Node<T> tmp = this.tail.prev;
        Node<T> node = new Node<>(item, this.tail, tmp);
        this.tail.prev = node;
        tmp.next = node;
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if (!this.isEmpty()) {
            Node<T> tmp = this.head.next;
            tmp.next.prev = this.head;
            this.head.next = tmp.next;
            this.size--;
            return tmp.val;
        }
        return null;
    }

    public T removeLast() {
        if (!this.isEmpty()) {
            Node<T> tmp = this.tail.prev;
            this.tail.prev = tmp.prev;
            tmp.prev.next = this.tail;
            this.size--;
            return tmp.val;
        }
        return null;
    }

    public void printDeque() {
        Node<T> tmp = this.head.next;
        while (tmp != this.tail) {
            System.out.println(tmp.val + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node<T> tmp = this.head.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.val;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node<T> tmp = this.head.next;
        return getRecursiveHelper(tmp, index, 0);

    }

    private T getRecursiveHelper(Node<T> node, int index, int cnt) {
        if (cnt == index) {
            return node.val;
        } else {
            return getRecursiveHelper(node.next, index, cnt + 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
        //
    }

    private class Itr implements Iterator<T> {
        private Node<T> tmp = head.next;
        int cursor;
        int lastRet = -1;
        T lastVal;

        Itr() {
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                lastRet = cursor;
                lastVal = tmp.val;
                cursor++;
                tmp = tmp.next;
                return lastVal;
            }
            return null;
        }
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof deque.Deque)) {
            return false;
        }
        deque.Deque<Object> other = (deque.Deque<Object>) o;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }
}
