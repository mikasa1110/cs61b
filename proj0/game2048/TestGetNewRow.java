package game2048;

import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the emptySpaceExists() static method of Model.
 *
 * @author mikasa
 */

public class TestGetNewRow {

    @Test
    public void test() {
        int[][] rawVals = new int[][] {
                {16, 2, 0, 4},
                {0, 2, 4, 0},
                {8, 0, 0, 0},
                {0, 0, 0, 4},
        };
        Model model = new Model(rawVals, 0, 0, false);
        model.printBoard();
        // 真正的row = size-1-我眼里的row
        // 所谓的向上是row++
        // 所以getRow那是从size-2开始搜到0的
        assertEquals(3,model.getNewRow(0,3,0));
        assertEquals(3,model.getNewRow(2,2,0));
        assertEquals(2,model.getNewRow(1,0,0));
    }

}
