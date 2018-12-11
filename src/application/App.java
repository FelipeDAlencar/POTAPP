package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import control.ControllerLogin;
import dao.DAOCliente;
import dao.DAOFuncionario;
import dao.DAOVenda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Cliente;
import model.Funcionario;
import model.Venda;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLLogin.fxml"));

		try {

			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> param) {
					return new ControllerLogin(primaryStage);
				}
			});
			Pane root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			

			primaryStage.setTitle("Login do Usuario");
			primaryStage.setResizable(false);
			primaryStage.show();

			// Funcionario funcionario = new Funcionario("Felipe", 14200, "felipe", "123",
			// true);
			// DAOFuncionario daoFuncionario = new DAOFuncionario();
			// daoFuncionario.insert(funcionario);

		} catch (IOException e) {

			e.printStackTrace();
		}

		
//		 public static String gerarCodigoInterno(String nome, String ultimoCodigo) {
//
//		        if (ultimoCodigo.length() != 0) {
//		            int parteNumerica = Integer.parseInt(ultimoCodigo.substring(3));
//		            parteNumerica += 1;
//		            String codigo = nome.substring(0, 3) + String.format("%03d", parteNumerica);
//		            return codigo;
//		        }
//
//		        return  nome.substring(0, 3) + String.format("%03d", "1");
//		    }	
		}

	public static void main(String[] args) {
		launch(args);
	}
}
