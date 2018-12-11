package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.MascarasTF;
import model.Produto;
import view.Alerta;

public class ControllerCadastroProdutoDialog implements Initializable {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfQuantidade;

	@FXML
	private TextField tfPreco;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	private Produto produto;
	private Stage stage;
	private boolean clicado;

	@FXML
	void acaoBtnCancelar(ActionEvent event) {
		stage.close();
	}

	@FXML
	void acaoBtnConfirmar(ActionEvent event) {
		if (validar()) {
			produto.setNome(tfNome.getText());
			produto.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
			produto.setPreco(Double.parseDouble(tfPreco.getText()));
			produto.setAtivo(true);
			clicado = true;
			stage.close();

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfPreco.setText("");
		tfQuantidade.setText("");

		MascarasTF.mascaraNumero(tfPreco);
		MascarasTF.mascaraNumeroInteiro(tfQuantidade);

	}

	public boolean validar() {
		String errorMessage = "";

		if (tfNome.getText() == null || tfNome.getText().length() == 0) {
			errorMessage += "Nome inválido\n";
		}
		if (tfQuantidade.getText() == null || tfQuantidade.getText().length() == 0) {
			errorMessage += "Quatidade inválida\n";
		}
		if (tfPreco.getText() == null || tfPreco.getText().length() == 0) {
			errorMessage += "Preco inválido\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alerta.getInstace(AlertType.ERROR, "Erro ao validar", "Campos Invalidos.", errorMessage);
			return false;
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {

		this.produto = produto;
		
		if (produto.getId() != null) {
			tfNome.setText(produto.getNome());
			tfQuantidade.setText(String.valueOf(produto.getQuantidade()));
			tfPreco.setText(String.valueOf(produto.getPreco()));
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public boolean isClicado() {
		return clicado;
	}

	public void setClicado(boolean clicado) {
		this.clicado = clicado;
	}

}
