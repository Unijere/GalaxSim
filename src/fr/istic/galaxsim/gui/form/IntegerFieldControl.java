package fr.istic.galaxsim.gui.form;

import javafx.scene.control.TextField;

import java.util.Optional;

public class IntegerFieldControl extends FieldControl {

    private final TextField field;
    private Optional<Integer> lowBound;
    private Optional<Integer> highBound;

    public IntegerFieldControl(TextField field) {
        this.field = field;

        // Seuls les chiffres sont acceptes, les autres sont effaces
        field.textProperty().addListener((obs, oldValue, newValue) -> {
            field.setText(newValue.replaceAll("[^0-9]", ""));
        });
    }

    public IntegerFieldControl(TextField field, int lowBound, int hightBound) {
        this(field);

        setHighBound(hightBound);
        setLowBound(lowBound);
    }

    public int getHighBound() {
        return highBound.get();
    }

    public int getLowBound() {
        return lowBound.get();
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

        return (lowBound.isPresent() && value >= getLowBound()) && (highBound.isPresent() && value < getHighBound());
    }

    public void setHighBound(int value) {
        highBound = Optional.of(value);
    }

    public void setLowBound(int value) {
        lowBound = Optional.of(value);
    }

}
