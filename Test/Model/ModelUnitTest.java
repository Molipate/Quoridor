package Model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

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

    @Test
    public void testResetGame(){

        Model model = new Model();

        model.resetGame();

        Assert.assertEquals(model.getGameState(),1);
        Assert.assertEquals(model.getTypeWall(),-1);
        Assert.assertEquals(model.getTypePlayer(),-1);
        Assert.assertEquals(model.getActivePlayer(),1);
        Assert.assertEquals(model.getNbWallsJ1(),10);
        Assert.assertEquals(model.getNbWallsJ2(),10);
    }

    @Test
    public void testIsWallSelect(){

        Model model = mock(Model.class);

        Mockito.when(model.getTypeWall()).thenReturn(-1);
        Assert.assertFalse(model.getTypeWall() != -1);

        Mockito.when(model.getTypeWall()).thenReturn(1);
        Assert.assertTrue(model.getTypeWall() != -1);
    }

    @Test
    public void testIsPlayerSelected(){

        Model model = mock(Model.class);

        Mockito.when(model.getTypePlayer()).thenReturn(-1);
        Assert.assertFalse(model.getTypePlayer() != -1);

        Mockito.when(model.getTypePlayer()).thenReturn(1);
        Assert.assertTrue(model.getTypePlayer() != -1);

    }


}
