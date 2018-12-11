package model;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class MascarasTF {
	
	
	
	public static void mascaraNumeroInteiro(TextField textField) {

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public static void mascaraNumero(TextField textField) {

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            newValue = newValue.replaceAll(",", ".");
            if (newValue.length() > 0) {
                try {
                    Double.parseDouble(newValue);
                    textField.setText(newValue.replaceAll(",", "."));
                } catch (Exception e) {
                    textField.setText(oldValue);
                }
            }
        });

    }
}
