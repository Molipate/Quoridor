package Model;

/**
 * Created by molipate on 07/12/15.
 */
public class Model {

    private int gameState;

    private int typeWall;
    private int typePlayer;

    private int activePlayer;
    private int nbWallsJ1;
    private int nbWallsJ2;

    private Board board;



    private boolean win;

    private AssetsImage assetsImage;

    public Model(){

        board = new Board(this);

        assetsImage = new AssetsImage(this);

        gameState = 0;
        typeWall = -1;
        typePlayer = -1;

        activePlayer = 1;
        nbWallsJ1 = 10;
        nbWallsJ2 = 10;


        win = false;
    }

    //TESTED BY MOLIPATE
    public void resetGame(){


        gameState = 1;
        typeWall = -1;
        typePlayer = -1;

        activePlayer = 1;
        nbWallsJ1 = 10;
        nbWallsJ2 = 10;

        board.resetGame();

        win = false;
    }

    public AssetsImage getAssetsImage(){ return assetsImage; }

    public Board getBoard(){ return board;}

    public int getGameState(){
        return gameState;
    }



    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    //TESTED
    public boolean isWallSelect() {
        return typeWall != -1;
    }

    //TESTED
    public boolean isPlayerSelected(){
        return typePlayer != -1;
    }

    public void selectWall(int i) {
        typeWall = i;
        board.makeAllowedIntersection();
    }

    public void selectPlayer(int k, int i, int j){
        typePlayer = k;
        makeAllowedMove(i, j);
    }

    public void makeAllowedMove(int i, int j){
        board.makeAllowedMove(i,j);
    }

    public void placeWall(int i, int j) {
        board.setWall(i,j,typeWall);
        typeWall = -1;

        if(activePlayer == 1)
            nbWallsJ1--;
        else
            nbWallsJ2--;
        /*
        if (board.playersNotBlocked()) {
            board.lastPutWall = i * 10 + j;
            if(activePlayer == 1)
                nbWallsJ1--;
            else
                nbWallsJ2--;
        }
        else board.getWall()[i][j]=0;
        board.lastPutWall=0;

        if (board.lastPutWall!=0)
            changePlayer();
        else
            board.degageWall(i,j);
        */
        changePlayer();
    }

    public void movePlayer(int i, int j){
        for (int k = 0; k < 9; k++) {
            for (int l = 0; l < 9; l++) {
                if(board.getPlateau(k,l) == typePlayer){
                    board.setPlateau(k,l,0);
                    board.setPlateau(i,j,typePlayer);
                    typePlayer = -1;
                    isLaWin(i);
                }
            }
        }
        changePlayer();
    }

    private void isLaWin(int i) {
        if(i == 8 && activePlayer == 1){
            win = true;
            changePlayer();
        }

        if(i == 0 && activePlayer == 2){
            win = true;
            changePlayer();
        }
    }

    public boolean getWin(){
        return win;
    }

    public int getActivePlayer(){
        return activePlayer;
    }

    public void changePlayer(){
        if(activePlayer == 1)
            activePlayer = 2;
        else
            activePlayer = 1;
    }

    public int getNbWall() {
        if(activePlayer == 1)
            return nbWallsJ1;
        else
            return nbWallsJ2;
    }

    public int getTypeWall() {
        return typeWall;
    }

    public int getTypePlayer() {
        return typePlayer;
    }

    public int getNbWallsJ2() {
        return nbWallsJ2;
    }

    public int getNbWallsJ1() {
        return nbWallsJ1;
    }
}
