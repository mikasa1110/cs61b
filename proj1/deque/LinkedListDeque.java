package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {

    public static class Node<T> {
        public T val;
        public Node<T> next;
        public Node<T> prev;
        public Node(T val, Node<T> next, Node<T> prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public Node<T> head;
    public Node<T> tail;
    public int size;
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
    public boolean isEmpty() {
        return this.size == 0;
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
        return getRecursiveHelper(tmp,index,0);

    }

    public T getRecursiveHelper(Node<T> node, int index, int cnt) {
        if (cnt==index) {
            return node.val;
        } else {
            return getRecursiveHelper(node.next, index, cnt+1);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
        //
    }

    private class Itr implements Iterator<T> {
        private Node<T> tmp = head.next;
        int cursor;
        int lastRet= -1;

        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            if (hasNext()) {
                lastRet = cursor;
                cursor++;
                tmp = tmp.next;
                return tmp.val;
            }
            return null;
        }
    }



    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque && ((LinkedListDeque<?>) o).size == this.size) {
            Node<T> tmp = this.head.next;
            Node<?> tmp2 = ((LinkedListDeque<?>) o).head.next;
            for (int i = 0; i < this.size; i++) {
                if (tmp.val != tmp2.val) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
