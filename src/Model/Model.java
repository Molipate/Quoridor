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

    private static int nbWall_J1,nbWall_J2,nbWall_J3,nbWall_J4,active_Player,gameState;

    private boolean win;

    private AssetsImage assetsImage;

    public Model(){

        board = new Board(this);

        assetsImage = new AssetsImage(this);

        gameState=0;

        win = false;
    }

    //TESTED BY MOLIPATE
    public void resetGame(){
        board.initPlateau();
        nbWall_J1=MAX_WALL;
        nbWall_J2=MAX_WALL;
        nbWall_J3=MAX_WALL;
        nbWall_J4=MAX_WALL;
        active_Player=J1;
        gameState=1;
        win = false;
    }

    public AssetsImage getAssetsImage(){ return assetsImage; }

    public Board getBoard(){ return board;}

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int gameState) {
        Model.gameState = gameState;
    }

    public int getJ1() {
        return J1;
    }

    public int getJ2() {
        return J2;
    }

    public int getJ3() {
        return J3;
    }

    public int getJ4() {
        return J4;
    }

    public int getWALL() {
        return WALL;
    }

    public int getMAX_WALL() {
        return MAX_WALL;
    }

    public static int getActive_Player() {
        return active_Player;
    }

    public static void setActive_Player(int active_Player) {
        Model.active_Player = active_Player;
    }

    public static int getNbWall_J1() {
        return nbWall_J1;
    }

    public static void setNbWall_J1(int nbWall_J1) {
        Model.nbWall_J1 = nbWall_J1;
    }

    public static int getNbWall_J2() {
        return nbWall_J2;
    }

    public static void setNbWalll_J2(int nbWalll_J2) {
        Model.nbWall_J2 = nbWalll_J2;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getNbWall() {
        switch (active_Player){
            case J1: return nbWall_J1;
            case J2: return nbWall_J2;
            case J3: return nbWall_J3;
            case J4: return nbWall_J4;
            default: return 0;
        }
    }
}
