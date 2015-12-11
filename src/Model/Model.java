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

    private int[][] plateau;
    private int[][] wall;

    private boolean[][] freeWall;
    private boolean[][] freeMove;

    public Model(){
        gameState = 0;
        typeWall = -1;
        typePlayer = -1;

        activePlayer = 1;
        nbWallsJ1 = 10;
        nbWallsJ2 = 10;

        plateau = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                plateau[i][j] = 0;

        wall = new int[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                wall[i][j] = 0;

        freeWall = new boolean[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                freeWall[i][j] = true;

        freeMove = new boolean[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                freeMove[i][j] = false;

        plateau[0][4] = 1;
        plateau[8][4] = 2;
    }

    public int getGameState(){
        return gameState;
    }

    public int getPlateau(int i, int j){
        return plateau[i][j];
    }

    public int getWall(int i, int j){
        return wall[i][j];
    }

    public boolean isWallFree(int i, int j){
        return freeWall[i][j];
    }

    public boolean isMoveFree(int i, int j){
        return freeMove[i][j];
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public boolean isWallSelect() {
        return typeWall != -1;
    }

    public boolean isPlayerSelected(){
        return typePlayer != -1;
    }

    public void selectWall(int i) {
        typeWall = i;
        makeAllowedIntersection();
    }

    public void selectPlayer(int k, int i, int j){
        typePlayer = k;
        makeAllowedMove(i, j);
    }

    private void makeAllowedIntersection() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(wall[i][j] != 0){
                    freeWall[i][j] = false;
                }
            }
        }
    }

    private void makeAllowedMove(int i, int j){

        for (int k = 0; k <9; k++)
            for (int l = 0; l < 9; l++)
                freeMove[k][l] = false;

        if(i>=0 || i<9 || j>=0 || j<9){
            if(i>0 && plateau[i-1][j]==0)freeMove[i-1][j]=true;
            if(i<8 && plateau[i+1][j]==0)freeMove[i+1][j]=true;
            if(j>0 && plateau[i][j-1]==0)freeMove[i][j-1]=true;
            if(j<8 && plateau[i][j+1]==0)freeMove[i][j+1]=true;

            if(i>0 &&((j>0 && wall[i-1][j-1]==1)
                    ||(j<8 && wall[i-1][j]==1)))freeMove[i-1][j]=false;
            if(j>0 && ((i>0 && wall[i-1][j-1]==2)
                    ||(i<8 &&wall[i][j-1]==2)))freeMove[i][j-1]=false;
            if(i<8 &&((j<8 && wall[i][j]==1)
                    ||(j>0 && wall[i][j-1]==1)))freeMove[i+1][j]=false;
            if(j<8 &&((i<8 && wall[i][j]==2)
                    ||(i>0 && wall[i-1][j]==2)))freeMove[i][j+1]=false;
        }
    }

    public void placeWall(int i, int j) {
        wall[i][j] = typeWall;
        typeWall = -1;

        if(activePlayer == 1)
            nbWallsJ1--;
        else
            nbWallsJ2--;

        changePlayer();
    }

    public void movePlayer(int i, int j){
        for (int k = 0; k < 9; k++) {
            for (int l = 0; l < 9; l++) {
                if(plateau[k][l] == typePlayer){
                    plateau[k][l] = 0;
                    plateau[i][j] = typePlayer;
                    typePlayer = -1;
                }
            }
        }
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
}
