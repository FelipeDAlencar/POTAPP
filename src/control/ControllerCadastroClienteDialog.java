package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import view.Alerta;

public class ControllerCadastroClienteDialog implements Initializable {
	@FXML
	private Label lbNome;

	@FXML
	private Label lbCPF;

	@FXML
	private Label lbTelefone;

	@FXML
	private Label lbCodigo;

	@FXML
	private TextField tfCodigo;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfCPF;

	@FXML
	private TextField tfTelefone;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	private Stage dialogStage;
	private boolean buttonConfirmarCliked = false;
	private Cliente cliente;

	@FXML
	void acaoBtnCancelar(ActionEvent event) {
		dialogStage.close();

	}

	@FXML
	void acaoBtnConfirmar(ActionEvent event) {
		if (validar()) {
			cliente.setCodigo(Integer.parseInt(tfCodigo.getText()));
			cliente.setNome(tfNome.getText());
			cliente.setCpf(tfCPF.getText());
			cliente.setTelefone(tfTelefone.getText());
			buttonConfirmarCliked = true;
			dialogStage.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isButtonConfirmarCliked() {
		return buttonConfirmarCliked;
	}

	public void setButtonConfirmarCliked(boolean buttonConfirmarCliked) {
		this.buttonConfirmarCliked = buttonConfirmarCliked;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		this.tfCodigo.setText(String.valueOf(cliente.getCodigo()));
		this.tfNome.setText(cliente.getNome());
		this.tfCPF.setText(cliente.getCpf());
		this.tfTelefone.setText(cliente.getTelefone());
	}

	private boolean validar() {
		String errorMessage = "";

		if (tfCodigo.getText() == null || tfCodigo.getText().length() == 0) {
			errorMessage += "Codigo inválido\n";
		}
		if (tfNome.getText() == null || tfNome.getText().length() == 0) {
			errorMessage += "Nome inválido\n";
		}
		if (tfCPF.getText() == null || tfCPF.getText().length() == 0) {
			errorMessage += "CPF inválido\n";
		}
		if (tfTelefone.getText() == null || tfTelefone.getText().length() == 0) {
			errorMessage += "Telefone inválido\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alerta.getInstace(AlertType.ERROR, "Erro ao validar", "Campos Invalidos.", errorMessage);
			return false;
		}
	}

}
