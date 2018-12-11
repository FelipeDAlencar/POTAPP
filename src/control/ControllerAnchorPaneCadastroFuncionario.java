	package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.DAOFuncionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Funcionario;
import view.Alerta;

public class ControllerAnchorPaneCadastroFuncionario implements Initializable {
	@FXML
	private TableView<Funcionario> tableView;

	@FXML
	private TableColumn<Funcionario, String> colunaNome;

	@FXML
	private TableColumn<Funcionario, String> colunaLogin;

	@FXML
	private TableColumn<Funcionario, Boolean> colunaSuperUsuario;

	@FXML
	private Label lnNome;

	@FXML
	private Label lbMatricula;

	@FXML
	private Label lnLogin;

	@FXML
	private Label lbSuperUsuario;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	private ArrayList<Funcionario> funcionarios;
	private ObservableList<Funcionario> obsFuncionarios;
	private DAOFuncionario daoFuncionario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		daoFuncionario = new DAOFuncionario();
		carregarFuncionarios();

		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionouDaTabela(newValue));
	}

	@FXML
	void acaoBtns(ActionEvent event) {
		if (event.getSource() == btnInserir) {
			Funcionario funcionario = new Funcionario();
			boolean confirmar = exibirTelaCadastroFuncionarioDialog(funcionario);

			if (confirmar) {
				daoFuncionario.insert(funcionario);
				carregarFuncionarios();
				System.out.println("Adicionado");
			}
		} else if (event.getSource() == btnEditar) {
			Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
			if (funcionario != null) {
				boolean confirmar = exibirTelaCadastroFuncionarioDialog(funcionario);
				if (confirmar) {
					daoFuncionario.update(funcionario);
					System.out.println("Editado");
					carregarFuncionarios();

				}
			} else {
				Alerta.getInstace(AlertType.CONFIRMATION, "Erro de seleção", "Selecioanar Funcionario",
						"Selecione na tabela " + "ao lado o funcionario a ser alterado.");
			}
		} else if (event.getSource() == btnExcluir) {
			Funcionario funcionario = tableView.getSelectionModel().getSelectedItem();
			if (funcionario != null) {
				daoFuncionario.delete(funcionario.getId());
				carregarFuncionarios();
			}else {
				Alerta.getInstace(AlertType.CONFIRMATION, "Erro de seleção", "Selecioanar Funcionario", "Selecione na tabela "
						+ "ao lado o funcionario a ser excluido.");
			}
		}

	}

	private boolean exibirTelaCadastroFuncionarioDialog(Funcionario funcionario) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerAnchorPaneCadastroFuncionarioDialog.class
				.getResource("/view/FXMLAnchorPaneCadastroFuncionarioDialog.fxml"));

		Pane pane;
		Stage stage = null;
		try {
			pane = loader.load();
			Scene scene = new Scene(pane);
			stage = new Stage();
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ControllerAnchorPaneCadastroFuncionarioDialog controller = loader.getController();

		controller.setFuncionario(funcionario);
		controller.setStage(stage);

		stage.showAndWait();

		return controller.isConfirmar();
	}

	private void selecionouDaTabela(Funcionario funcionario) {
		if (funcionario != null) {
			lbMatricula.setText(String.valueOf(funcionario.getMatricula()));
			lbSuperUsuario.setText(String.valueOf(funcionario.isSuperUsuario()));
			lnLogin.setText(funcionario.getLogin());
			lnNome.setText(funcionario.getNome());

		}
	}

	private void carregarFuncionarios() {

		funcionarios = daoFuncionario.select();

		colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaSuperUsuario.setCellValueFactory(new PropertyValueFactory<>("superUsuario"));

		obsFuncionarios = FXCollections.observableArrayList(funcionarios);

		tableView.setItems(obsFuncionarios);

	}

}
