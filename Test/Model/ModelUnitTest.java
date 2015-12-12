package Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ModelUnitTest {

    @Test
    public void ModelTest(){
        Model model = new Model();
        int[][] plateau=new int[9][9];
        plateau[0][4]=1;
        plateau[8][4]=2;

        boolean[][] tabBoolean= new boolean[8][8];
        for (boolean[] row: tabBoolean)
            Arrays.fill(row, Boolean.TRUE);


        Assert.assertEquals(model.getGameState(),0);
        Assert.assertEquals(model.getTypeWall(),-1);
        Assert.assertEquals(model.getTypePlayer(),-1);
        Assert.assertEquals(model.getActivePlayer(),1);
        Assert.assertEquals(model.getNbWallsJ1(),10);
        Assert.assertEquals(model.getNbWallsJ2(),10);
        Assert.assertArrayEquals(model.getPlateau(),plateau);
        Assert.assertArrayEquals(model.getWall(),new int[8][8]);
        Assert.assertArrayEquals(model.getFreeWall(),tabBoolean);
        Assert.assertArrayEquals(model.getFreeMove(),new boolean[9][9]);

    }
}
