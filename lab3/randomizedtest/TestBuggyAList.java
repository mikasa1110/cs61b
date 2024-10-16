package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing simple = new AListNoResizing();
        BuggyAList complex = new BuggyAList();
        for (int i = 0; i < 3; i++) {
            simple.addLast(i+4);
            complex.addLast(i+4);
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(simple.removeLast(), complex.removeLast());
        }

    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> complex = new BuggyAList();
        int N = 50000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0,3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0,100);
                L.addLast(randVal);
                complex.addLast(randVal);
//                System.out.println("addLast("+randVal+")");
            } else if (operationNumber == 1) {
                int size = L.size();
                if (size>=1) {
                    L.removeLast();
                    complex.removeLast();
//                    System.out.println("removeLast()");
                }
            } else if (operationNumber == 2) {
                int size = L.size();
                if (size>=1) {
                    int val = L.getLast();
                    int val2 = complex.getLast();
//                    System.out.println("getLast()"+val);
                    assertEquals(val, val2);
                }
            }


        }
    }


}
