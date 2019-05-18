package fr.istic.galaxsim.gui.form;

import javafx.scene.control.TextField;

public class IntegerFieldControl {

    private final TextField field;
    private boolean hasHigherBound;
    private boolean hasLowerBound;
    private int lowBound;
    private int highBound;

    public IntegerFieldControl(TextField field) {
        this(field, 0, 0);
        hasBounds = false;
    }

    public IntegerFieldControl(TextField field, int lowBound, int hightBound) {
        this.field = field;
        this.lowBound = lowBound;
        this.highBound = hightBound;
        this.hasBounds = true;

        // Seuls les chiffres sont acceptes, les autres sont effaces
        field.textProperty().addListener((obs, oldValue, newValue) -> {
            field.setText(newValue.replaceAll("[^0-9]", ""));
        });
    }

    public int getHighBound() {
        return highBound;
    }

    public int getLowBound() {
        return lowBound;
    }

    /**
     * Recupere la valeur du champ de texte et la convertir en un entier
     *
     * @return la valeur du champ
     */
    public int getValue() {
        return Integer.parseInt(field.getText());
    }

    /**
     * Determine si la valeur du champ est bien comprise entre la borne inferieure (inclue)
     * et la borne superieure (exclue)
     *
     * @return true si la valeur est comprise dans l'interval, false sinon
     */
    public boolean isValid() {
        int value = getValue();

        return value >= lowBound && value < highBound;
    }

    public void setHighBound(int highBound) {
        this.highBound = highBound;
    }

    public void setLowBound(int lowBound) {
        this.lowBound = lowBound;
    }

}
