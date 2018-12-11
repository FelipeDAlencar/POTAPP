package control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.ldap.Rdn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;

public class ControllerAnchorPaneCadastroFuncionarioDialog implements Initializable {
	
	private Stage stage;
	private Funcionario funcionario;
	private boolean confirmar;
	
	
	@FXML
    private TextField tfNome;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private RadioButton rbSuperUsuario;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    @FXML
    void acoesDeBtns(ActionEvent event) {
    	if(event.getSource() == btnCancelar) {
    		stage.close();
    	}else if(event.getSource() == btnConfirmar) {
    		funcionario.setMatricula(Integer.parseInt(tfMatricula.getText()));
    		funcionario.setLogin(tfLogin.getText());
    		funcionario.setSenha(pfSenha.getText());
    		funcionario.setSuperUsuario(rbSuperUsuario.isSelected());
    		funcionario.setNome(tfNome.getText());
    		
    		confirmar = true;
    		stage.close();
    	}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
		
		if(funcionario.getId() != null) {
			tfLogin.setText(funcionario.getLogin());
			tfMatricula.setText(String.valueOf(funcionario.getMatricula()));
			tfNome.setText(funcionario.getNome());
			pfSenha.setText(funcionario.getSenha());
			rbSuperUsuario.setSelected(funcionario.isSuperUsuario());
		}
	}
	public boolean isConfirmar() {
		return confirmar;
	}
	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}
	
	
	

}
