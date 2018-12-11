package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import sql.SQLConnection;
import sql.SQLUtil;

public class DAOCliente {
	private Connection conexao;
	private PreparedStatement statement;
	private static DAOCliente dao;

	public static DAOCliente getInstace() {
		if (dao == null) {
			dao = new DAOCliente();
		}
		return dao;
	}

	public void insert(Cliente cliente) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Cliente.INSERT);

			statement.setInt(1, cliente.getCodigo());
			statement.setString(2, cliente.getNome());
			statement.setString(3, cliente.getCpf());
			statement.setString(4, cliente.getTelefone());

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Cliente cliente) {

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Cliente.UPDATE);
			statement.setInt(1, cliente.getCodigo());
			statement.setString(2, cliente.getNome());
			statement.setString(3, cliente.getTelefone());
			statement.setString(4, cliente.getCpf());
			statement.setInt(5, cliente.getId());

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Cliente.DELETE);
			statement.setInt(1, id);

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Cliente> selectAll() {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Cliente.SELECT);

			ResultSet resultSet = statement.executeQuery();
			ArrayList<Cliente> clientes = new ArrayList<>();
			Cliente cliente;

			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getInt("id"), resultSet.getInt("codigo"), resultSet.getString("nome"),
						resultSet.getString("telefone"), resultSet.getString("cpf"));
				clientes.add(cliente);
			}

			conexao.close();
			return clientes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Cliente select_id(Cliente cliente) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Cliente.SELECT_ID);
			statement.setInt(1, cliente.getId());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cliente = new Cliente(resultSet.getInt("id"), resultSet.getInt("codigo"), resultSet.getString("nome"),
						resultSet.getString("telefone"), resultSet.getString("cpf"));
			}
			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
