package model;

public class ItemDeVenda {
	
	private Integer id;
	private Integer id_venda;
	private Integer id_produto;
	private int quantidade;
	private double valor;
	private Produto produto;
	private Venda venda;
	
	
	public ItemDeVenda() {

	}


	public ItemDeVenda(Integer id, int quantidade, double valor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}


	public ItemDeVenda(int quantidade, double valor) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Venda getVenda() {
		return venda;
	}


	public void setVenda(Venda venda) {
		this.venda = venda;
	}


	public Integer getId_venda() {
		return id_venda;
	}


	public void setId_venda(Integer id_venda) {
		this.id_venda = id_venda;
	}


	public Integer getId_produto() {
		return id_produto;
	}


	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}
	
	
	
}
