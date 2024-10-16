package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Deque<T> {
//    private T[] array;
//    private int head;
//    private int tail;
//    private int arraySize;
//    private int size;
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
//        array = (T[]) new Object[8];
//        head = -1;
//        tail = 0;
//        arraySize = 8;
//        size = 0;
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = null;
        for (T element:this) {
            if (max == null) {
                max = element;
            } else if (comparator.compare(max, element) < 0) {
                max = element;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = null;
        for (T element:this) {
            if (max == null) {
                max = element;
            }  else if (comparator.compare(max, element) < 0) {
                max = element;
            }
        }
        return max;
    }

}
