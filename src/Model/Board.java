package Model;

/**
 * Created by molipate on 17/12/15.
 */
public class Board {

    private Model model;

    private int[][] plateau;
    private int[][] wall;

    private boolean[][] freeWall;
    private boolean[][] freeMove;

    private int iJ1,jJ1,iJ2,jJ2;

    private boolean done = false;
    private boolean[][] visited;

    public Board(Model m){
        this.model=m;

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

        iJ1=0;
        iJ2=8;
        jJ1=4;
        jJ2=4;

    }

    public void resetGame() {

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                plateau[i][j] = 0;
                freeMove[i][j] = false;
            }
        }

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                wall[i][j] = 0;
                freeWall[i][j] = true;
            }
        }

        plateau[0][4] = 1;
        plateau[8][4] = 2;

        iJ1=0;
        iJ2=8;
        jJ1=4;
        jJ2=4;

    }



    public void makeAllowedIntersection() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                freeWall[i][j] = true;
                if(wall[i][j] == 0){
                    switch (model.getTypeWall()){
                        case 1:
                            if(i > 0 && wall[i - 1][j] == 1)
                                freeWall[i][j] = false;
                            if(i < 7 && wall[i + 1][j] == 1)
                                freeWall[i][j] = false;
                            break;
                        case 2:
                            if(j > 0 && wall[i][j - 1] == 2)
                                freeWall[i][j] = false;
                            if(j < 7 && wall[i][j + 1] == 2)
                                freeWall[i][j] = false;
                            break;
                    }
                }
            }
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(int i,int j,int value) {
        plateau[i][j] = value;
    }

    public int[][] getWall() {
        return wall;
    }

    public void setWall(int i,int j,int value) {
        wall[i][j]=value;
    }

    public boolean[][] getFreeWall() {
        return freeWall;
    }

    public void setFreeWall(boolean[][] freeWall) {
        this.freeWall = freeWall;
    }

    public boolean[][] getFreeMove() {
        return freeMove;
    }

    public void setFreeMove(boolean[][] freeMove) {
        this.freeMove = freeMove;
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

    public void makeAllowedMove(int i,int j) {
        for (int k = 0; k <9; k++)
            for (int l = 0; l < 9; l++)
                freeMove[k][l] = false;

        if(i<0 || i>9 || j<0 || j>9) return;
        if(i>0 && plateau[i-1][j]==0)freeMove[i-1][j]=true;
        if(i<8 && plateau[i+1][j]==0)freeMove[i+1][j]=true;
        if(j>0 && plateau[i][j-1]==0)freeMove[i][j-1]=true;
        if(j<8 && plateau[i][j+1]==0)freeMove[i][j+1]=true;

        if(i>0 &&((j>0 && wall[i-1][j-1]==2)
                ||(j<8 && wall[i-1][j]==2)))freeMove[i-1][j]=false;
        if(j>0 && ((i>0 && wall[i-1][j-1]==1)
                ||(i<8 &&wall[i][j-1]==1)))freeMove[i][j-1]=false;
        if(i<8 &&((j<8 && wall[i][j]==2)
                ||(j>0 && wall[i][j-1]==2)))freeMove[i+1][j]=false;
        if(j<8 &&((i<8 && wall[i][j]==1)
                ||(i>0 && wall[i-1][j]==1)))freeMove[i][j+1]=false;
    }




/*
    private boolean makeAllowedIntersection() {
        for (int x = 0; x <= 8; x++)
            for (int y = 0; y <= 8; y++)
                visited[x][y] = false;
        done = false;
        done = solve(iJ2, jJ2,2);
        if (!done) return false;
        done = solve(iJ1,jJ1,1);
        return done;
    }
*/
    private boolean solve(int x, int y,int idj) {
        if (x == 0 || y == 0 || x == 9 || y == 9) return false;
        if (visited[x][y]) return false;
        visited[x][y] = true;
        if(idj==1)
            if(x==0)
                done = true;
        if(idj==2)
            if(x==8)
                done = true;

        if (done) return true;


        if (freeWall[x][y+1]) solve(x, y + 1,idj);
        if (freeWall[x+1][y]) solve(x + 1, y,idj);
        if (freeWall[x][y-1]) solve(x, y - 1,idj);
        if (freeWall[x-1][y]) solve(x - 1, y,idj);
        return false;
    }
}
