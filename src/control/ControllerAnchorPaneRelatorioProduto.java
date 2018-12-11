package control;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.DAOProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sql.SQLConnection;

public class ControllerAnchorPaneRelatorioProduto implements Initializable {
	 @FXML
	    private TableView<Produto> tableViewProdutos;

	    @FXML
	    private TableColumn<Produto, Integer> colunaCodigo;

	    @FXML
	    private TableColumn<Produto, String> colunaNome;

	    @FXML
	    private TableColumn<Produto, Double> colunaPreco;

	    @FXML
	    private TableColumn<Produto, Integer> ColunaQuantidade;

	    @FXML
	    private Button btnImprimir;
	    
	    private DAOProduto daoProduto = DAOProduto.getInstace();
	    private ArrayList<Produto> produtos;
	    private ObservableList<Produto> obsProdutos;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			carregarTableViewProdutos();
			
		}

	    @FXML
	    void acaoBtnImprimir(ActionEvent event) throws JRException {
	    	
	    	Connection conexao =  SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
	    	
	    	URL url = getClass().getResource("/relatorios/RelatorioProdutos.jasper");
	    	JasperReport jasperReport = (JasperReport)JRLoader.loadObject(url);
	    	
	    	//null por que não estamos usando filtro.
	    	
	    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conexao);
	    	
	    	
	    	//false pq não deixa feixar a aplicação principal
	    	JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
	    	jasperViewer.setVisible(true);
	    }
	    
	    public void carregarTableViewProdutos() {
	    	colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    	colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
	    	ColunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
	    	
	    	produtos = daoProduto.select();
	    	
	    	obsProdutos = FXCollections.observableArrayList(produtos);
	    	
	    	tableViewProdutos.setItems(obsProdutos);
	    	
	    	
	    }
	    
	
}
