package model;

public class Funcionario {
	private Integer id;
	private String nome;
	private int matricula;
	private String login;
	private String senha;
	private boolean superUsuario;

	public Funcionario() {
		
	}

	public Funcionario(String nome, int matricula, String login, String senha, boolean superUsuario) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.login = login;
		this.senha = senha;
		this.superUsuario = superUsuario;
	}

	public Funcionario(Integer id, String nome, int matricula, String login, String senha, boolean superUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.login = login;
		this.senha = senha;
		this.superUsuario = superUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		this.superUsuario = superUsuario;
	}
	
	
}
