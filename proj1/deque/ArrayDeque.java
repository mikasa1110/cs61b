package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, deque.Deque<T> {
    private T[] array;
    private int head;
    private int tail;
    private int arraySize;
    private int size;
    private static final int DECBOUND = 16;
    private static final double INCFACTOR = 1.25;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        head = -1;
        tail = 0;
        arraySize = 8;
        size = 0;
    }

    public int size() {
        // tail是下一个可以插入的位置，head是当前有效元素的位置
        // tail....head ....
        return size;
    }


    public void addFirst(T item) {
        // head是当前有效的
        if (head == -1) {
            array[0] = item;
            tail += 1;
            head += 1;
            size += 1;
        } else {
            array[(head - 1 + arraySize) % arraySize] = item;
            head = (head - 1 + arraySize) % arraySize;
            size += 1;
        }
        if (head == tail) {
            incSize();
        }
    }

    private void incSize() {

        int newArraySize = (int) Math.round(arraySize * INCFACTOR);
        if (head < tail) {
            T[] temp = (T[]) new Object[newArraySize];
            System.arraycopy(array, head, temp, 0, arraySize);
            array = temp;
            head = 0;
            tail = size;
            arraySize = newArraySize;
        } else {
            T[] temp = (T[]) new Object[newArraySize];
            // tail....head...end
            System.arraycopy(array, head, temp, 0, arraySize - head);
            System.arraycopy(array, 0, temp, arraySize - head, tail);
            array = temp;
            head = 0;
            tail = size;
            arraySize = newArraySize;
        }
    }

    public void addLast(T item) {
        // tail永远是下一个
        if (head == -1) {
            head += 1;
        }
        array[tail] = item;
        tail = (tail + 1) % arraySize;
        size += 1;
        if (head == tail || tail == arraySize) {
            incSize();
        }
    }

    public T removeFirst() {
        if (!isEmpty()) {

            T value = array[head];
            size -= 1;
            if (size == 0) {
                head = -1;
                tail = 0;
            } else {
                head = (head + 1) % arraySize;
            }
            if (arraySize >= DECBOUND && size < Math.round(arraySize / 4)) {
                decSize();
            }

            return value;
        }
        return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            // tail是下一个
            T value = array[(tail - 1 + arraySize) % arraySize];
            tail = (tail - 1 + arraySize) % arraySize;
            size -= 1;
            if (arraySize >= DECBOUND && size < Math.round(arraySize / 4)) {
                decSize();
            }
            return value;

        }
        return null;
    }

    private T getFirst() {
        if (!isEmpty()) {
            return array[head];
        }
        return null;
    }

    private T getLast() {
        if (!isEmpty()) {
            return array[(tail - 1 + arraySize) % arraySize];
        }
        return null;
    }

    public void printDeque() {
        int start = head;
        while (start != tail) {
            System.out.println(array[head] + " ");
            head = (head + 1) % arraySize;
        }
        System.out.println();
    }

    public T get(int index) {
        if (index >= 0 && index < size) {
            int realIndex = (head + index) % arraySize;
            return array[realIndex];
        }
        return null;
    }

    private void decSize() {
        int newArraySize = (int) Math.round(arraySize * 0.25);
        if (head < tail) {
            T[] temp = (T[]) new Object[newArraySize];
            System.arraycopy(array, head, temp, 0, size);
            array = temp;
            head = 0;
            tail = size;
            arraySize = newArraySize;
        } else {
            T[] temp = (T[]) new Object[newArraySize];
            System.arraycopy(array, head, temp, 0, arraySize - head);
            System.arraycopy(array, 0, temp, arraySize - head, tail);
            array = temp;
            head = 0;
            tail = size;
            arraySize = newArraySize;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
        //
    }

    private class Itr implements Iterator<T> {
        int cursor = head;
        int lastRet = -1;

        Itr() {
        }

        @Override
        public boolean hasNext() {
            return cursor != tail && cursor != -1;
        }

        @Override
        public T next() {
            if (hasNext()) {
                lastRet = cursor;
                cursor = (cursor + 1 + arraySize) % arraySize;
                return array[lastRet];
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

        deque.Deque<?> other = (deque.Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = ((deque.Deque<?>) other).iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }
}
