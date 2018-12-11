package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerVboxPrincipal implements Initializable {
	@FXML
	private MenuItem miCadastroFuncionario;

	@FXML
	private MenuItem miCadastroProduto;

	@FXML
	private MenuItem miVendasPorMes;

	@FXML
	private MenuItem miQuantidadeDeProdutosEstoque;
	@FXML
	private MenuItem miCadastroCliente;

	@FXML
	private MenuItem miVendas;
	@FXML
	private AnchorPane AnchorPAane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void hundleMenuItemCadastroClientes() {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroClientes.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void acaoMiProcessoVendas(ActionEvent event) {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneProcessosVendas.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void acaoMiGrafico(ActionEvent event) {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void acaoMIQauntidadeProdutos(ActionEvent event) {

		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneRelatoriosProdutos.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void acaoMiCadastroProduto(ActionEvent actionEvent) {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroProdutos.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@FXML
	void acaoMiFuncionario(ActionEvent event) {
		System.out.println("Aqui");
		try {
			
			Pane pane = FXMLLoader.load(getClass().getResource("/view/FXMLAnchorPaneCadastroFuncionario.fxml"));
			AnchorPAane.getChildren().setAll(pane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MenuItem getMiCadastroFuncionario() {
		return miCadastroFuncionario;
	}

	public void setMiCadastroFuncionario(MenuItem miCadastroFuncionario) {
		this.miCadastroFuncionario = miCadastroFuncionario;
	}

	public MenuItem getMiCadastroProduto() {
		return miCadastroProduto;
	}

	public void setMiCadastroProduto(MenuItem miCadastroProduto) {
		this.miCadastroProduto = miCadastroProduto;
	}

	public MenuItem getMiVendasPorMes() {
		return miVendasPorMes;
	}

	public void setMiVendasPorMes(MenuItem miVendasPorMes) {
		this.miVendasPorMes = miVendasPorMes;
	}

	public MenuItem getMiQuantidadeDeProdutosEstoque() {
		return miQuantidadeDeProdutosEstoque;
	}

	public void setMiQuantidadeDeProdutosEstoque(MenuItem miQuantidadeDeProdutosEstoque) {
		this.miQuantidadeDeProdutosEstoque = miQuantidadeDeProdutosEstoque;
	}

	public MenuItem getMiCadastroCliente() {
		return miCadastroCliente;
	}

	public void setMiCadastroCliente(MenuItem miCadastroCliente) {
		this.miCadastroCliente = miCadastroCliente;
	}

	public MenuItem getMiVendas() {
		return miVendas;
	}

	public void setMiVendas(MenuItem miVendas) {
		this.miVendas = miVendas;
	}
	
	
	
}
