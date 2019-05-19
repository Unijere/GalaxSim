package fr.istic.galaxsim.gui.form;

import java.util.ArrayList;

public class FormControl {

    private ArrayList<FieldControl> controls = new ArrayList<FieldControl>();

    public FormControl() {

    }

    public boolean isValid() {
        for(FieldControl control : controls) {
            if(!control.isValid()) {
                return false;
            }
        }

        return true;
    }

}
