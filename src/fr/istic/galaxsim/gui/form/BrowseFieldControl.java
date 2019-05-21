package fr.istic.galaxsim.gui.form;

import java.io.File;

public class BrowseFieldControl extends FieldControl {

    private final BrowseField field;

    public BrowseFieldControl(BrowseField field) {
        this.field = field;
    }

    @Override
    public void hideError() {
        field.hideError();
    }

    @Override
    public boolean isValid() {
        // @OTODO Afficher un message d'erreur
        File file = field.getFile();
        if(file == null || !file.isFile()) {
            return false;
        }
        return true;
    }

    @Override
    public void showError() {
        field.showError();
    }
}
