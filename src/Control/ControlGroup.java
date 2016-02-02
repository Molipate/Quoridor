package Control;

import Model.Model;
import View.View;
/**
 * Created by molipate on 07/12/15.
 */
public class ControlGroup {

    private Model model;
    private View view;


    public ControlGroup(Model model,View view) {

        this.model = model;
        this.view = view;

        ControlInterface controleInterface = new ControlInterface(model, view);
        ControlBoard controleBoard = new ControlBoard(model, view);
        ControlWall controleWall = new ControlWall(model, view);

        view.setActionListener(controleInterface,controleBoard,controleWall);
        view.display(true);
    }
}
