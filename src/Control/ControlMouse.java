package Control;

import Model.Model;
import Model.Board;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by molipate on 07/12/15.
 */
public class ControlMouse extends Control implements ActionListener {

    private int wall_Orientation;
    private boolean wall_click;

    public ControlMouse(Model model, View view){
        super(model, view);
        view.setMouseListener(this);
        wall_click=false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == view.getNewGame())
            view.startNewGame();

        if(actionEvent.getSource() == view.getQuitGame())
            view.leave();

        if(!model.isWin()){
            if(actionEvent.getSource() == view.getVerticalWall() && model.hasWalls() && !wall_click) {
                System.out.println("-> Selected Vertical Wall");
                wall_Orientation = Board.VERTICAL;
                model.getBoard().getWallPossibles(wall_Orientation);
                wall_click=true;
                view.updateView();
                view.wallsPossible();
            }

            if(actionEvent.getSource() == view.getHorizontalWall() && model.hasWalls() && !wall_click){
                System.out.println("-> Selected Horizontal Wall");
                wall_Orientation = Board.HORIZONTAL;
                model.getBoard().getWallPossibles(wall_Orientation);
                wall_click=true;
                view.updateView();
                view.wallsPossible();
            }
        }

        for (int i = 0; i < 17; i+=2) {
            for (int j = 0; j < 17; j+=2) {
                if (actionEvent.getSource() == view.getPlateau(i, j)) {

                    if((board.getPlateau(i, j) == model.getActive_Player()) && !model.isWin()) {
                        System.out.println("-> Placing pawn at I : " + i + " - J : " + j);
                        model.getBoard().getMovePossibles(model.getActive_Player());
                        //model.getBoard().print();
                        view.updateView();
                        view.movesPossible();
                    }else if(model.getBoard().isMoveFree(i,j)){

                        model.getBoard().move_Player(model.getActive_Player(),i,j);
                        model.switchPlayer();
                        System.out.println("-> Placing pawn at u i: " + i + " - j: " + j);
                        //model.getBoard().print();
                        view.updateView();
                        view.updatePlayer();
                    }

                }
            }
        }
        for (int i = 1; i < 16; i+=2) {
            for (int j = 1; j < 16; j+=2) {
                if (actionEvent.getSource() == view.getPlateau(i, j)) {
                    if(model.getBoard().isWallFree(i, j) && model.hasWalls() && wall_click){
                        System.out.println("-> Placing wall at I : " + i + " - J : " + j);

                        if(board.placeWall(i, j,wall_Orientation)){
                            model.updateNbWall();
                            model.switchPlayer();
                        }
                        view.updateView();
                        view.updateWalls();
                        wall_click=false;
                    }
                }
            }
        }
    }
}
