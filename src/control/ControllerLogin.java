package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Funcionario;
import view.Alerta;

public class ControllerLogin implements Initializable {
	@FXML
	private TextField tfLogin;

	@FXML
	private Button btnEntrar;

	@FXML
	private PasswordField pfSenha;

	private DAOFuncionario daoFuncionario;
	private ControllerVboxPrincipal controllerPrincipal;
	private Stage stage;

	public ControllerLogin(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void AcaoBtnEntrar(ActionEvent event) {

		Funcionario funcionario = new Funcionario();
		funcionario.setLogin(tfLogin.getText());
		funcionario.setSenha(pfSenha.getText());
		funcionario = daoFuncionario.fyndLogin(funcionario);

		if (funcionario != null) {
			exibirTelaPrincipal();
			stage.close();
			if (funcionario.isSuperUsuario() == false) {
				controllerPrincipal.getMiCadastroFuncionario().setDisable(true);
				controllerPrincipal.getMiVendasPorMes().setDisable(true);
				controllerPrincipal.getMiQuantidadeDeProdutosEstoque().setDisable(true);
			}
		} else {
			Alerta.getInstace(AlertType.WARNING, "Falha", "Falha ao tentar logar.",
					"" + "certifique-se de que a senha e o login est�o corretas.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		daoFuncionario = new DAOFuncionario();
		// controllerPrincipal = new ControllerVboxPrincipal();
	}

	public void exibirTelaPrincipal() {
		Pane root;
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControllerVboxPrincipal.class.getResource("/view/FXMLVboxPrincipal.fxml"));
			root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			controllerPrincipal = loader.getController();

			stage.setTitle("Login do Usu�rio");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
