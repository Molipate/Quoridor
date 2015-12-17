package Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ModelUnitTest {

    @Test
    public void ModelTest(){
        Model model = new Model();



        Assert.assertEquals(model.getGameState(),0);
        Assert.assertEquals(model.getTypeWall(),-1);
        Assert.assertEquals(model.getTypePlayer(),-1);
        Assert.assertEquals(model.getActivePlayer(),1);
        Assert.assertEquals(model.getNbWallsJ1(),10);
        Assert.assertEquals(model.getNbWallsJ2(),10);

    }
}
