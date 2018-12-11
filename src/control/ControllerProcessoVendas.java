package control;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.DAOProduto;
import dao.DAOVenda;
import dao.DaoItemDeVenda;
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
import model.ItemDeVenda;
import model.Produto;
import model.Venda;
import sql.SQLConnection;
import view.Alerta;

public class ControllerProcessoVendas implements Initializable {
	@FXML
	private TableView<Venda> tableViewVendas;

	@FXML
	private TableColumn<Venda, String> colunaCodigo;

	@FXML
	private TableColumn<Venda, String> colunaData;

	@FXML
	private TableColumn<Venda, String> colunaCliente;

	@FXML
	private Label lbCodigo;

	@FXML
	private Label lbData;

	@FXML
	private Label lbValor;

	@FXML
	private Label lbPago;

	@FXML
	private Label lbClienteVenda;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnRemover;

	private ArrayList<Venda> vendas;
	private ObservableList<Venda> obsVendas;
	private DAOVenda daoVenda = DAOVenda.getInstace();
	private DaoItemDeVenda daoItemDeVenda = DaoItemDeVenda.getInstace();
	private DAOProduto daoProduto = DAOProduto.getInstace();

	@FXML
	void acaoBtnAlterar(ActionEvent event) {

	}

	@FXML
	void acaoBtnInserir(ActionEvent event) {
		Venda venda = new Venda();
		boolean confirmarClicked = exibirFXMLAnchorPaneProcessoVendaDialog(venda);
		if (confirmarClicked) {
			try {
				daoVenda.insert(venda);
				for (ItemDeVenda item : venda.getItemDeVendas()) {
					Produto produto = item.getProduto();
					item.setVenda(daoVenda.buscarUltimaVenda());
					daoItemDeVenda.insert(item);
					produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
					daoProduto.update(produto);
				}

				daoVenda.getConexao().commit();
				carregarTvVendas();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@FXML
	void acaoBtnRemover(ActionEvent event) {
		Venda venda = tableViewVendas.getSelectionModel().getSelectedItem();
		if (venda != null) {

			for (ItemDeVenda item : venda.getItemDeVendas()) {
				
				Produto produto = item.getProduto();
				produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
				daoProduto.update(produto);
				daoItemDeVenda.delete(item.getId());

			}
			daoVenda.delete(venda.getId());
			try {
				daoVenda.getConexao().commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			carregarTvVendas();

		} else {
			Alerta.getInstace(AlertType.WARNING, "Erro", "", "Por favor, escolha uma venda na tabela.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarTvVendas();

		tableViewVendas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionouNaTabela(newValue));

	}

	private void selecionouNaTabela(Venda venda) {
		

		
		if (venda != null) {
			lbCodigo.setText(String.valueOf(venda.getId()));
			lbData.setText(String.valueOf(venda.getData()));
			lbPago.setText(String.valueOf(venda.isPago()));
			lbValor.setText(String.valueOf(venda.getValor()));
			lbClienteVenda.setText(venda.getCliente().toString());
		} else {
			lbCodigo.setText("");
			lbData.setText("");
			lbPago.setText("");
			lbValor.setText("");
			lbClienteVenda.setText("");
		}

	}

	private void carregarTvVendas() {
		
		colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));

		vendas = daoVenda.select();

		obsVendas = FXCollections.observableArrayList(vendas);

		tableViewVendas.setItems(obsVendas);

	}

	private boolean exibirFXMLAnchorPaneProcessoVendaDialog(Venda venda) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					ControllerProcessoVendaDialog.class.getResource("/view/FXMLAnchorPaneProcessoVendaDialog.fxml"));
			Pane pane = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Registro de Vendas");
			Scene scene = new Scene(pane);
			stage.setScene(scene);

			ControllerProcessoVendaDialog controller = loader.getController();
			controller.setStage(stage);
			controller.setVenda(venda);

			stage.showAndWait();

			return controller.isClicouConfirmar();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
