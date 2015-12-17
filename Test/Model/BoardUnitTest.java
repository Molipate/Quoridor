package Model;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BoardUnitTest {

    @Test
    public void BoardTest(){
        Model model = new Model();
        Board board = model.getBoard();

        boolean[][] freeMove = new boolean[9][9];

        for(int i=0;i<9;i++)
            Arrays.fill(freeMove[i],false);

        Assert.assertArrayEquals(board.getFreeMove(),freeMove);
    }

}
