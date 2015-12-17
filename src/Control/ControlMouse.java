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

        if(!model.getWin()){
            if(actionEvent.getSource() == view.getVerticalWall()) {
                System.out.println("-> Selected Vertical Wall");
                if(!model.isWallSelect())
                    model.selectWall(1);
                else
                    model.selectWall(-1);

                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++)
                        if (model.getPlateau(i, j) != 0 && model.isPlayerSelected())
                            model.selectPlayer(-1, i, j);

                view.makeView();
            }

            if(actionEvent.getSource() == view.getHorizontalWall()){
                System.out.println("-> Selected Vertical Wall");
                if(!model.isWallSelect())
                    model.selectWall(2);
                else
                    model.selectWall(-1);

                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++)
                        if (model.getPlateau(i, j) != 0 && model.isPlayerSelected())
                            model.selectPlayer(-1, i, j);

                view.makeView();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (actionEvent.getSource() == view.getPlateau(i, j)) {

                    if((model.getPlateau(i, j) == model.getActivePlayer() || model.isPlayerSelected()) && !model.getWin()) {

                        if (model.getPlateau(i, j) != 0)
                            if (!model.isPlayerSelected())
                                model.selectPlayer(model.getPlateau(i, j), i, j);
                            else
                                model.selectPlayer(-1, i, j);
                        else if (model.isMoveFree(i, j))
                            model.movePlayer(i, j);
                        else
                            model.selectPlayer(-1, i, j);

                        if (model.isWallSelect())
                            model.selectWall(-1);
                    }
                    view.makeView();
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (actionEvent.getSource() == view.getIntersection(i, j)) {
                    if(model.isWallSelect() && model.isWallFree(i, j)){
                        System.out.println("-> Placing wall at I : " + i + " - J : " + j);
                        model.placeWall(i, j);
                        view.makeView();
                    }
                }
            }
        }
    }
}
