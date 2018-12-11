package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.ItemDeVenda;
import model.Produto;
import model.Venda;
import sql.SQLConnection;
import sql.SQLUtil;

public class DaoItemDeVenda {
	private Connection conexao;
	private PreparedStatement statement;
	private static DaoItemDeVenda dao;

	public static DaoItemDeVenda getInstace() {
		if (dao == null) {
			dao = new DaoItemDeVenda();
		}
		return dao;
	}

	public void insert(ItemDeVenda itemDeVenda) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.ItemDeVenda.INSERT);
			
			statement.setInt(1, itemDeVenda.getQuantidade());
			statement.setDouble(2, itemDeVenda.getValor());
			statement.setInt(3, itemDeVenda.getProduto().getId());
			statement.setInt(4, itemDeVenda.getVenda().getId());

			statement.execute();
			//conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void update(Cliente cliente) {
//
//		try {
//			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
//			statement = conexao.prepareStatement(SQLUtil.Cliente.UPDATE);
//			statement.setInt(1, cliente.getCodigo());
//			statement.setString(2, cliente.getNome());
//			statement.setString(3, cliente.getTelefone());
//			statement.setString(4, cliente.getCpf());
//			statement.setInt(5, cliente.getId());
//
//			statement.execute();
//			conexao.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public void delete(int id) {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.ItemDeVenda.DELETE);
			statement.setInt(1, id);

			statement.execute();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ItemDeVenda> select() {
		try {
			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
			statement = conexao.prepareStatement(SQLUtil.ItemDeVenda.SELECT);

			ResultSet resultSet = statement.executeQuery();
			ArrayList<ItemDeVenda> itemDeVendas = new ArrayList<>();
			ItemDeVenda itemDeVenda =  new ItemDeVenda();
			
			while (resultSet.next()) {
				
				itemDeVenda.setId(resultSet.getInt("id"));
				itemDeVenda.setId_produto(resultSet.getInt("id_produto"));
				itemDeVenda.setId_venda(resultSet.getInt("id_venda"));
				itemDeVenda.setQuantidade(resultSet.getInt("quantidade"));
				itemDeVenda.setValor(resultSet.getDouble("valor"));
				
				Produto produto = new Produto();
				Venda venda = new Venda();
				
				produto.setId(itemDeVenda.getId_produto());
				venda.setId(itemDeVenda.getId());
				
				DAOProduto daoProduto = DAOProduto.getInstace();
				DAOVenda daoVenda = DAOVenda.getInstace();
				
				produto = daoProduto.select_id(produto);
				venda = daoVenda.select_id(venda);
				
				itemDeVenda.setProduto(produto);
				itemDeVenda.setVenda(venda);
				
				itemDeVendas.add(itemDeVenda);
			}

			conexao.close();
			return itemDeVendas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<ItemDeVenda> select_por_venda(Venda venda) {
        
        try {
        	conexao =  SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
            statement = conexao.prepareStatement(SQLUtil.ItemDeVenda.SELECT_POR_VENDA);
            statement.setInt(1, venda.getId());
            ResultSet resultado = statement.executeQuery();
            ArrayList<ItemDeVenda> itemDeVendas = new ArrayList<>();
            while (resultado.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                Produto produto = new Produto();
                Venda venda2 = new Venda();
                itemDeVenda.setId(resultado.getInt("id"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getDouble("valor"));
                
                produto.setId(resultado.getInt("id_produto"));
                venda2.setId(resultado.getInt("id_venda"));
                
                //Obtendo os dados completos do Produto associado ao Item de Venda
                DAOProduto produtoDAO = new DAOProduto();
                
                produto = produtoDAO.select_id(produto);
                
                itemDeVenda.setProduto(produto);
                itemDeVenda.setVenda(venda);
                
                itemDeVendas.add(itemDeVenda);
            }
            return itemDeVendas;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//	public Cliente select_id(Cliente cliente) {
//		try {
//			conexao = SQLConnection.getConnectionInstance(SQLConnection.NOME_BD_CONEXAO_POSTGRES);
//			statement = conexao.prepareStatement(SQLUtil.ItemDeVenda.SELECT_ID);
//			statement.setInt(1, cliente.getId());
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				cliente = new Cliente(resultSet.getInt("id"), resultSet.getInt("codigo"), resultSet.getString("nome"),
//						resultSet.getString("telefone"), resultSet.getString("cpf"));
//			}
//			return cliente;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
