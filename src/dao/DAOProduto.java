package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;
import sql.SQLConnection;
import sql.SQLUtil;

public class DAOProduto {
	private Connection conexao;
	private PreparedStatement statement;
	private static DAOProduto dao;

	public static DAOProduto getInstace() {
		if (dao == null) {
			dao = new DAOProduto();
		}
		return dao;
	}

	public void insert(Produto produto) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Produto.INSERT);

			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getPreco());
			statement.setString(1, produto.getNome());
			statement.setBoolean(4, produto.isAtivo());
			

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Produto produto) {

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Produto.UPDATE);
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getPreco());
			statement.setString(1, produto.getNome());
			statement.setBoolean(4, produto.isAtivo());
			
			statement.setInt(5, produto.getId());
			

			statement.execute();
			//conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Produto.DELETE);
			statement.setInt(1, id);

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Produto> select() {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Produto.SELECT);

			ResultSet resultSet = statement.executeQuery();
			ArrayList<Produto> produtos = new ArrayList<>();
			Produto produto = new Produto();

			while (resultSet.next()) {
				produto.setId(resultSet.getInt("id"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setPreco(resultSet.getDouble("valor"));
				produto.setNome(resultSet.getString("nome"));
				produtos.add(produto);
				produto = new Produto();
			}

			conexao.close();
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Produto select_id(Produto produto) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Produto.SELECT_ID);
			statement.setInt(1, produto.getId());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				produto = new Produto(resultSet.getInt("id"), resultSet.getString("nome"),resultSet.getFloat("valor"), resultSet.getInt("quantidade"));
			}
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
