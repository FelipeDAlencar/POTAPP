package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta{
	public static javafx.scene.control.Alert alert;
	
	public static void getInstace(AlertType tipo, String title, String cabecalho, String conteudo) {
		if(alert == null) {
			alert = new Alert(tipo);
		}
		alert.setAlertType(tipo);
		alert.setTitle(title);
		alert.setHeaderText(cabecalho);
		alert.setContentText(conteudo);
		alert.show();
	}

}
