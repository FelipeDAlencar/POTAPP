package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.DAOCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Cliente;
import view.Alerta;

public class ControllerCadastroCliente implements Initializable {

	@FXML
	private TableView<Cliente> tableViewCliente;

	@FXML
	private TableColumn<Cliente, String> colunaNome;

	@FXML
	private TableColumn<Cliente, String> colunaCPF;

	@FXML
	private Label lbCodigo;

	@FXML
	private Label lbNome;

	@FXML
	private Label laCPF;

	@FXML
	private Label lbTelefone;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	private ArrayList<Cliente> clientes;
	private ObservableList<Cliente> obsCliente;
	private DAOCliente daoCliente = DAOCliente.getInstace();
	private Alert alert = new Alert(AlertType.INFORMATION);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarClientes();

		tableViewCliente.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecinouItemDaTabela(newValue));
	}

	public void carregarClientes() {
		clientes = daoCliente.selectAll();

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		obsCliente = FXCollections.observableArrayList(clientes);

		tableViewCliente.setItems(obsCliente);

	}

	public void selecinouItemDaTabela(Cliente cliente) {
		if (cliente != null) {
			lbCodigo.setText(String.valueOf(cliente.getCodigo()));
			lbNome.setText(cliente.getNome());
			laCPF.setText(cliente.getCpf());
			lbTelefone.setText(cliente.getTelefone());
		} else {
			lbCodigo.setText("");
			lbNome.setText("");
			laCPF.setText("");
			lbTelefone.setText("");
		}
	}

	@FXML
	void acaoBtnEditar(ActionEvent event) {
		Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
		
		if (cliente != null) {

			boolean confirmarClicked = exibirTelaDecadastro(cliente);
			if (confirmarClicked) {
				daoCliente.update(cliente);
				carregarClientes();
				Alerta.getInstace(AlertType.INFORMATION, "Editar Cliente", "Edição de Clientes.","Cliente Editado com sucesso!");
			}

		} else {
			
			Alerta.getInstace(AlertType.ERROR, "Editar Cliente", "Atenção!.","Cliente não selecionado!");
			
		}
	}

	@FXML
	void acaoBtnInserir(ActionEvent event) {
		Cliente cliente = new Cliente();
		boolean confirmarCliked = exibirTelaDecadastro(cliente);
		if (confirmarCliked) {
			daoCliente.insert(cliente);
			carregarClientes();
			Alerta.getInstace(AlertType.INFORMATION, "Informação", "Inserção de Clientes!.","Cliente inserido com sucesso!!");

		}
	}
	@FXML
	void acaoBtnExcluir(ActionEvent event) {
		Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
		if(cliente != null) {
			daoCliente.delete(cliente.getId());
			carregarClientes();
		}else {
			Alerta.getInstace(AlertType.ERROR, "Deletar Cliente", "Atenção!.","Cliente não selecionado!");
		}
	}

	public boolean exibirTelaDecadastro(Cliente cliente) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControllerCadastroClienteDialog.class
					.getResource("/view/FXMLAnchorPaneCadastroClienteDialog.fxml"));
			Pane root = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastro de Clientes");
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);

			ControllerCadastroClienteDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCliente(cliente);
			

			dialogStage.showAndWait();

			return controller.isButtonConfirmarCliked();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
