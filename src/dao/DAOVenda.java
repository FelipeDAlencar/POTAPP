package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Cliente;
import model.Produto;
import model.Venda;
import sql.SQLConnection;
import sql.SQLUtil;

public class DAOVenda {
	private Connection conexao;
	private PreparedStatement statement;
	private static DAOVenda dao;

	public static DAOVenda getInstace() {
		if (dao == null) {
			dao = new DAOVenda();
		}
		return dao;
	}

	public void insert(Venda venda) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			conexao.setAutoCommit(false);
			statement = conexao.prepareStatement(SQLUtil.Venda.INSERT);

			statement.setInt(1, venda.getCliente().getId());
			statement.setDate(2, Date.valueOf(venda.getData()));
			statement.setFloat(3, venda.getValor());
			statement.setBoolean(4, venda.isPago());

			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Venda venda) {

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Venda.UPDATE);
			statement.setInt(1, venda.getId_Cliente());
			statement.setDate(2, Date.valueOf(venda.getData()));
			statement.setFloat(3, venda.getValor());
			statement.setBoolean(4, venda.isPago());
			statement.setInt(5, venda.getId());

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			conexao.setAutoCommit(false);
			statement = conexao.prepareStatement(SQLUtil.Venda.DELETE);
			statement.setInt(1, id);

			statement.execute();
			// conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Venda> select() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Venda.SELECT);

			ResultSet resultSet = statement.executeQuery();

			DAOCliente daoCliente = DAOCliente.getInstace();

			ArrayList<Venda> vendas = new ArrayList<>();
			Venda venda;
			Cliente cliente = new Cliente();

			while (resultSet.next()) {
				venda = new Venda(resultSet.getInt("id"), resultSet.getInt("id_Cliente"), resultSet.getFloat("valor"),
						resultSet.getBoolean("pago"),
						LocalDate.parse(String.valueOf((resultSet.getDate("data_venda")))));
				venda.setData(LocalDate.parse(String.valueOf(resultSet.getDate("data_venda"))));

				cliente.setId(venda.getId_Cliente());
				cliente = daoCliente.select_id(cliente);

				venda.setCliente(cliente);

				DaoItemDeVenda daoItemDeVenda = DaoItemDeVenda.getInstace();
				venda.setItemDeVendas(daoItemDeVenda.select_por_venda(venda));

				vendas.add(venda);

			}

			conexao.close();
			return vendas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Venda buscarUltimaVenda() {

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Venda.MAXID);
			ResultSet resultSet = statement.executeQuery();
			Venda venda = new Venda();
			if (resultSet.next()) {
				venda.setId(resultSet.getInt("max"));

			}
			// conexao.close();
			return venda;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public Venda select_id(Venda venda) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Venda.SELECT_ID);
			statement.setInt(1, venda.getId());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				venda = new Venda(resultSet.getInt("id"), resultSet.getInt("id_Cliente"), resultSet.getFloat("valor"),
						resultSet.getBoolean("pago"),
						LocalDate.parse(String.valueOf((resultSet.getDate("data_venda")))));
			}
			return venda;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<Integer, ArrayList> select_vendas_por_mes() {
		Map<Integer, ArrayList> retorno = new HashMap();

		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.Venda.SELECT_VENDASPORMES);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				ArrayList linha = new ArrayList();
				if (!retorno.containsKey(resultado.getInt("ano"))) {
					linha.add(resultado.getInt("mes"));
					linha.add(resultado.getInt("count"));
					retorno.put(resultado.getInt("ano"), linha);
				} else {
					ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
					linhaNova.add(resultado.getInt("mes"));
					linhaNova.add(resultado.getInt("count"));
				}
			}
			return retorno;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return retorno;

	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

}
