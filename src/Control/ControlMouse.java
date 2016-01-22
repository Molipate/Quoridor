package Control;

import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by molipate on 07/12/15.
 */
public class ControlMouse extends Control implements ActionListener {

    public ControlMouse(Model model, View view){
        super(model, view);
        view.setMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == view.getNewGame())
            view.startNewGame();

        if(actionEvent.getSource() == view.getQuitGame())
            view.leave();

        if(!model.isWin()){
            if(actionEvent.getSource() == view.getVerticalWall()) {
                System.out.println("-> Selected Vertical Wall");


                view.makeView();
            }

            if(actionEvent.getSource() == view.getHorizontalWall()){
                System.out.println("-> Selected Vertical Wall");


                view.makeView();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (actionEvent.getSource() == view.getPlateau(i, j)) {

                    if((board.getPlateau(i, j) == model.getActive_Player()) && !model.isWin()) {


                    }
                    view.makeView();
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (actionEvent.getSource() == view.getIntersection(i, j)) {
                    if(model.getBoard().isWallFree(i, j)){
                        System.out.println("-> Placing wall at I : " + i + " - J : " + j);
                        board.placeWall(i, j,board.getUP());
                        view.makeView();
                    }
                }
            }
        }
    }
}
