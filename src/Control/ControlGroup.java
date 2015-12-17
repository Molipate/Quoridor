package Control;

import Model.Model;
import View.View;
/**
 * Created by molipate on 07/12/15.
 */
public class ControlGroup {

    private Model model;
    private View view;


    public ControlGroup(Model model) {

        this.model = model;
        this.view = new View(this.model);

        ControlMouse controleMouse = new ControlMouse(model, view);
        view.display(true);
    }
}
