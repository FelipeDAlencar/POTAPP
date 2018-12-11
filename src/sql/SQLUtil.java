package sql;

public class SQLUtil {

	public static String USUARIO_POSTGRES = "postgres";
	public static String SENHA_POSTGRES = "81540106";
	public static String URL_POSTGRES = "jdbc:postgresql://localhost:5432/postgres";

	public class Funcionario {

		public static final String INSERT = "insert into  funcionario(nome, matricula,login, senha) values (?,?,?,?)";
		public static final String UPDATE = "update funcionario set nome = ?, matricula = ?, login = ?, senha = ?, super_usuario = ? where id = ?";
		public static final String DELETE = "delete from funcionario where id = ?";
		public static final String SELECT = "select * from funcionario ";
		public static final String FYNDLOGIN = "select * from funcionario where login = ? and senha = ? ";

	}

	public class Cliente {

		public static final String INSERT = "insert into  cliente(codigo, nome, telefone, cpf) values (?,?,?,?)";
		public static final String UPDATE = "update cliente set codigo = ?, nome = ?, telefone = ?, cpf = ? where id = ?";
		public static final String DELETE = "delete from cliente where id = ?";
		public static final String SELECT = "select * from cliente ";
		public static final String SELECT_ID = "select * from cliente where id = ?  ";

	}

	public class Venda {

		public static final String INSERT = "insert into  venda(id_cliente, data_venda, valor, pago) values (?,?,?,?)";
		public static final String UPDATE = "update venda set codigo_cliente = ?, data_venda = ?, valor = ?, pago = ? where id = ?";
		public static final String DELETE = "delete from venda where id = ?";
		public static final String SELECT = "select * from venda ";
		public static final String MAXID = "select MAX(id) from venda";
		public static final String SELECT_ID = "select * from venda where id = ?  ";
		public static final String SELECT_VENDASPORMES = ""
				+ "select count(id), extract(year from data_venda) as ano, extract (month from data_venda) as mes from venda group by ano,mes order by ano, mes";
	}

	public class Produto {

		public static final String INSERT = "insert into  produto(nome, quantidade, valor, ativo, super_usuario) values (?,?,?,?,?)";
		public static final String UPDATE = "update produto set  nome = ?, quantidade = ?,  valor = ?, ativo = ?  where id = ?";
		public static final String DELETE = "delete from produto where id = ?";
		public static final String SELECT = "select * from produto  where ativo = true";
		public static final String SELECT_ID = "select * from produto where id = ?";
	}

	public class ItemDeVenda {

		public static final String INSERT = "INSERT INTO itensdevenda(quantidade, valor, id_produto, id_venda) VALUES(?,?,?,?)";
	//	public static final String UPDATE = "update itensdevenda set codigo = ?, nome = ?, telefone = ?, cpf = ? where id = ?";
		public static final String DELETE = "delete from itensdevenda where id = ?";
		public static final String SELECT = "select * from itensdevenda";
		public static final String SELECT_ID = "select * from itensdevenda where id = ?  ";
		public static final String SELECT_POR_VENDA = "SELECT * FROM itensdevenda WHERE id_venda=?";

	}
}
