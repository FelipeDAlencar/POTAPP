package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;
import sql.SQLConnection;
import sql.SQLUtil;

public class DAOFuncionario {

	private Connection conexao;
	private PreparedStatement statement;
	private static DAOFuncionario dao;

	public static DAOFuncionario getInstace() {
		if (dao == null) {
			dao = new DAOFuncionario();
		}
		return dao;
	}

	public void insert(Funcionario funcionario) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Funcionario.INSERT);

			statement.setString(1, funcionario.getNome());
			statement.setInt(2, funcionario.getMatricula());
			statement.setString(3, funcionario.getLogin());
			statement.setString(4, funcionario.getSenha());
			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Funcionario funcionario) {

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Funcionario.UPDATE);
			statement.setString(1, funcionario.getNome());
			statement.setInt(2, funcionario.getMatricula());
			statement.setString(3, funcionario.getLogin());
			statement.setString(4, funcionario.getSenha());
			statement.setBoolean(5, funcionario.isSuperUsuario());
			statement.setInt(6, funcionario.getId());

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Funcionario.DELETE);
			statement.setInt(1, id);

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Funcionario> select() {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Funcionario.SELECT);
			
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Funcionario> funcionarios = new ArrayList<>();
			Funcionario funcionario;
			
			while(resultSet.next()) {
				funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getInt("matricula"), resultSet.getString("login"), resultSet.getString("senha"), resultSet.getBoolean("super_usuario"));
				funcionarios.add(funcionario);
			}
			
			conexao.close();
			return funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Funcionario fyndLogin(Funcionario funcionario) {
		conexao =  SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
		try {
			statement = conexao.prepareStatement(SQLUtil.Funcionario.FYNDLOGIN);
			statement.setString(1, funcionario.getLogin());
			statement.setString(2, funcionario.getSenha());	
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Funcionario funcionario2 =  new Funcionario();
				funcionario.setSuperUsuario(resultSet.getBoolean("super_usuario"));
				return funcionario;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
