package Control;

import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class ControlWall extends Control implements ActionListener{

    private String action;
    private String[] actionI;
    private int iw,jw,DIRECTION;
    private boolean[][] walls;
    public ControlWall(Model model, View view){
        super(model,view);
        DIRECTION =-1;
        walls = new boolean[board.getSIZE()][board.getSIZE()];
        actionI = new String[2];
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        action = actionEvent.getActionCommand();
        board.resetMoves();
        view.updateView();
        view.updatePlayer();
        if(model.hasWalls()){
            if(action=="V"){
                DIRECTION=board.getVERTICAL();
                walls = board.getWallPossibles(board.getVERTICAL());
                view.wallsPossible();
            }else if(action=="H"){
                DIRECTION=board.getHORIZONTAL();
                walls = board.getWallPossibles(board.getHORIZONTAL());
                view.wallsPossible();
            }else{
                if(DIRECTION!=-1){
                    actionI=action.split(",");
                    iw=Integer.parseInt(actionI[0]);
                    jw=Integer.parseInt(actionI[1]);
                    if(board.isWallFree(iw,jw)){
                        board.placeWall(iw,jw,DIRECTION);
                        model.updateNbWall();
                        model.switchPlayer();
                    }
                    System.out.println("{"+iw+"-"+jw+"}");
                    board.resetWalls();
                    view.updateView();
                    view.updateWalls();
                    view.wallsPossible();
                    DIRECTION=-1;
                }
            }
        }




    }
}
