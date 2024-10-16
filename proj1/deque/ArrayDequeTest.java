package deque;


import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addHeadToInc() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertEquals(deque.isEmpty(), true);
        deque.addFirst(1);
        assertEquals(deque.isEmpty(), false);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);
        assertEquals(9, deque.size());
    }

    @Test
    public void addTailToInc() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertEquals(deque.isEmpty(), true);
        deque.addLast(1);
        assertEquals(deque.isEmpty(), false);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        assertEquals(9, deque.size());
    }

    @Test
    public void randomTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int N = 10000000;
        for (int i = 0; i < N; i++) {
            int choice = StdRandom.uniform(0,5);
            // addFirst addTail, removeLast,removeTail
            if (choice==0) {
                deque.addLast(i);
            } else if (choice==1) {
                deque.addFirst(i);
            } else if (choice==2 && !deque.isEmpty()) {
                assertEquals(deque.getFirst(),deque.removeFirst());
            } else if (choice==3 && !deque.isEmpty()) {
                assertEquals(deque.getLast(),deque.removeLast());
            } else if (choice==4) {
                deque.get(StdRandom.uniform(0,deque.size()+5));
            }
        }
    }
}
