package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.DAOCliente;
import dao.DAOProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;
import model.ItemDeVenda;
import model.Produto;
import model.Venda;
import view.Alerta;

public class ControllerProcessoVendaDialog implements Initializable {

	@FXML
	private ComboBox<Cliente> cbCliente;

	@FXML
	private DatePicker dpDataVenda;

	@FXML
	private CheckBox chbPago;

	@FXML
	private TableView<ItemDeVenda> tableViewItens;

	@FXML
	private TableColumn<ItemDeVenda, Produto> colunaProduto;

	@FXML
	private TableColumn<ItemDeVenda, Integer> ColunaQuantidade;

	@FXML
	private TableColumn<ItemDeVenda, Double> colunaValorItem;

	@FXML
	private TextField tfValor;

	@FXML
	private ComboBox<Produto> cbProduto;

	@FXML
	private TextField tfQtdProduto;

	@FXML
	private Button btnAddItem;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnCancelar;

	private ArrayList<Cliente> clientes;
	private ArrayList<Produto> produtos;

	private ObservableList<Cliente> obsCliente;
	private ObservableList<Produto> obsProdutos;
	private ObservableList<ItemDeVenda> obsItensDeVenda;

	private DAOCliente daoCliente = DAOCliente.getInstace();
	private DAOProduto daoProduto = DAOProduto.getInstace();

	private Stage stage;
	private boolean clicouConfirmar;
	private Venda venda;

	@FXML
	void acaoBtnAddItem(ActionEvent event) {
		Produto produto;
		ItemDeVenda itemDeVenda = new ItemDeVenda();

		if (cbProduto.getSelectionModel().getSelectedItem() != null) {
			produto = (Produto) cbProduto.getSelectionModel().getSelectedItem();
			
			if (Integer.parseInt(tfQtdProduto.getText()) <= produto.getQuantidade()) {
				itemDeVenda.setProduto(produto);
				itemDeVenda.setQuantidade(Integer.parseInt(tfQtdProduto.getText()));
				itemDeVenda.setValor(produto.getPreco() * itemDeVenda.getQuantidade());
				venda.getItemDeVendas().add(itemDeVenda);
				venda.setValor(venda.getValor() + (float) itemDeVenda.getValor());

				obsItensDeVenda = FXCollections.observableArrayList(venda.getItemDeVendas());
				tableViewItens.setItems(obsItensDeVenda);
				tfValor.setText(String.format("%.2f", venda.getValor()));

			} else {
				Alerta.getInstace(AlertType.WARNING, "Problemas na escolha do produto.", "",
						"Não existe a quantidade de" + "deste produto disponivel no estoque.");
			}
		}
	}

	@FXML
	void acaoConfirmarVenda(ActionEvent event) {
		
		if (validaDados()) {
			venda.setCliente((Cliente) cbCliente.getSelectionModel().getSelectedItem());
			venda.setPago(chbPago.isSelected());
			venda.setData(dpDataVenda.getValue());
			clicouConfirmar = true;
			stage.close();
		}
	}

	private boolean validaDados() {
		String errorMessage = "";
		if(cbCliente.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Cliente invalido.";
		}
		if(dpDataVenda.getValue() == null) {
			errorMessage += "Data invalida.";
		}
		if(obsItensDeVenda == null) {
			errorMessage += "Itens de venda invalidos.";
		}
		
		if(errorMessage.length() == 0) {
			return true ;
		}else {
			Alerta.getInstace(AlertType.ERROR, "Erro", "Campos de preenchimentos invalidos.", errorMessage);
			return false;
		}
	}

	@FXML
	void acaocancelarVenda(ActionEvent event) {
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarcbClientes();
		carregarProdutos();
		colunaProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
		colunaValorItem.setCellValueFactory(new PropertyValueFactory<>("valor"));
		ColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	}

	public void carregarcbClientes() {
		clientes = daoCliente.selectAll();
		obsCliente = FXCollections.observableArrayList(clientes);

		cbCliente.setItems(obsCliente);

	}

	public void carregarProdutos() {
		produtos = daoProduto.select();
		obsProdutos = FXCollections.observableArrayList(produtos);
		cbProduto.setItems(obsProdutos);

	}

	public boolean isClicouConfirmar() {
		return clicouConfirmar;
	}

	public void setClicouConfirmar(boolean clicouConfirmar) {
		this.clicouConfirmar = clicouConfirmar;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
