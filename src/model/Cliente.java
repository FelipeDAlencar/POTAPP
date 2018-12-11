package model;

public class Cliente {
	private Integer id;
	private int codigo;
	private String nome;
	private String telefone;
	private String cpf;

	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	public Cliente(int codigo, String nome, String telefone, String cpf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public Cliente(Integer id, int codigo, String nome, String telefone, String cpf) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
