package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.lowagie.tools.ToolboxAvailable;

import dao.DAOProduto;
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
import model.Produto;
import view.Alerta;

public class ControllerCadastroProdutos implements Initializable {

	@FXML
	private TableView<Produto> tableView;

	@FXML
	private TableColumn<Produto, String> colunaNome;

	@FXML
	private TableColumn<Produto, Integer> colunaQuantidade;

	@FXML
	private TableColumn<Produto, Double> colunaPreco;

	@FXML
	private Label lbCodigo;

	@FXML
	private Label lbNome;

	@FXML
	private Label lbQuantidade;

	@FXML
	private Label lbPreco;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnRemover;

	private ArrayList<Produto> produtos;
	private ObservableList<Produto> obsProdutos;
	private DAOProduto dao = DAOProduto.getInstace();

	@FXML
	void acaoBtnAtualizar(ActionEvent event) {
		
		Produto produto = tableView.getSelectionModel().getSelectedItem();

		boolean cliclou = exibirTelaDeCadastro(produto);
		if (produto != null) {
			if (cliclou) {
				dao.update(produto);
				carregarProdutos();
				Alerta.getInstace(Alert.AlertType.INFORMATION, "Cadastro produto", "Alteração de produto",
						"alteração realizado com sucesso!");
			}
		} else {
			Alerta.getInstace(AlertType.WARNING, "Cadastro produto", "Alteração de produto",
					"Para alterar deve-se selecionar o produto na tabela.");
		}
	}

	@FXML
	void acaoBtnInserir(ActionEvent event) {
		Produto produto = new Produto();

		boolean clicado = exibirTelaDeCadastro(produto);

		if (clicado) {
			dao.insert(produto);
			carregarProdutos();
			Alerta.getInstace(AlertType.INFORMATION, "Cadastro de produtos", "Inserção de produto",
					"Inserção realizada com sucesso!");

		}

	}

	@FXML
	void acaoBtnRemover(ActionEvent event) {
		try {
			Produto produto = tableView.getSelectionModel().getSelectedItem();
			produto.setAtivo(false);
			dao.update(produto);
			carregarProdutos();

		} catch (Exception e) {
			Alerta.getInstace(AlertType.WARNING, "Cadastro produto", "Alteração de produto",
					"Para alterar deve-se selecionar o produto na tabela.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarProdutos();

		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionouNaTabela(newValue));

	}

	public void carregarProdutos() {
		produtos = dao.select();

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		obsProdutos = FXCollections.observableArrayList(produtos);

		tableView.setItems(obsProdutos);

	}

	public void selecionouNaTabela(Produto produto) {
		if (produto != null) {
			lbCodigo.setText(String.valueOf(produto.getId()));
			lbNome.setText(produto.getNome());
			lbQuantidade.setText(String.valueOf(produto.getQuantidade()));
			lbPreco.setText(String.valueOf(produto.getPreco()));
		} else {
			lbCodigo.setText("");
			lbNome.setText("");
			lbQuantidade.setText("");
			lbPreco.setText("");
		}
	}

	public boolean exibirTelaDeCadastro(Produto produto) {
		try {
			FXMLLoader load = new FXMLLoader();
			load.setLocation(ControllerCadastroProdutoDialog.class
					.getResource("/view/FXMLAnchorPaneCadastroProdutoDialog.fxml"));
			Pane root = load.load();

			Stage stage = new Stage();
			stage.setTitle("Cadastro Produtos");
			Scene scene = new Scene(root);
			stage.setScene(scene);

			ControllerCadastroProdutoDialog controllerCadastroProdutoDialog = load.getController();
			controllerCadastroProdutoDialog.setProduto(produto);
			controllerCadastroProdutoDialog.setStage(stage);

			stage.showAndWait();

			return controllerCadastroProdutoDialog.isClicado();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
