package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements deque.Deque<T> {

    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {

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
            }  else if (c.compare(max, element) < 0) {
                max = element;
            }
        }
        return max;
    }

}
