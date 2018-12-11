package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	public static final String NOME_BD_CONEXAO_POSTGRES = "POSTGRES";
	
	

	private static Connection conexao = null;

	public static synchronized Connection getConnectionInstance(String bd) {

		try {
			if (conexao == null || conexao.isClosed()) {
				switch (bd) {
				case NOME_BD_CONEXAO_POSTGRES:
					conexao =(Connection) DriverManager.getConnection(SQLUtil.URL_POSTGRES, SQLUtil.USUARIO_POSTGRES,
							SQLUtil.SENHA_POSTGRES);
					break;

				default:
					break;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conexao;

	}

}
