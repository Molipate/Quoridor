package Model;

/**
 * Created by molipate on 07/12/15.
 */
public class Model {

    private Board board;

    public final int J1=1,
                     J2=2,
                     J3=3,
                     J4=4,
                     WALL=9,
                     MAX_WALL=10;

    private static int nbWall_J1,nbWalll_J2;

    private boolean win;

    private AssetsImage assetsImage;

    public Model(){

        board = new Board(this);

        assetsImage = new AssetsImage(this);

        win = false;
    }

    //TESTED BY MOLIPATE
    public void resetGame(){
        board.initPlateau();
        nbWall_J1=MAX_WALL;
        nbWalll_J2=MAX_WALL;
        win = false;
    }

    public AssetsImage getAssetsImage(){ return assetsImage; }

    public Board getBoard(){ return board;}

}
