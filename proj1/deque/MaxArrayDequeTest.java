package deque;
import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

import static org.junit.Assert.*;
public class MaxArrayDequeTest {

    @Test
    public void test() {
        Comparator<Integer> intComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<Integer>(intComparator);
        maxArrayDeque.addFirst(12);
        maxArrayDeque.addFirst(13);
        maxArrayDeque.addFirst(15);
        int a = maxArrayDeque.max();
        assertEquals(15,a);
    }
}
