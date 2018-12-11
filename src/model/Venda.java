package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
	private Integer id;
	private Integer id_Cliente;
	private float valor;
	private boolean pago;
	private LocalDate data;
	private Cliente cliente;
	private ArrayList<ItemDeVenda> itemDeVendas;

	public Venda() {
		itemDeVendas = new ArrayList<>();

	}

	public Venda(Integer id_Cliente, float valor, boolean pago) {
		super();
		this.id_Cliente = id_Cliente;
		this.valor = valor;
		this.pago = pago;

		itemDeVendas = new ArrayList<>();
	}

	public Venda(Integer id, Integer id_Cliente, float valor, boolean pago, LocalDate date) {
		super();
		this.id = id;
		this.id_Cliente = id_Cliente;
		this.valor = valor;
		this.pago = pago;
		this.data = date;

		itemDeVendas = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(Integer id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<ItemDeVenda> getItemDeVendas() {
		return itemDeVendas;
	}

	public void setItemDeVendas(ArrayList<ItemDeVenda> itemDeVendas) {
		this.itemDeVendas = itemDeVendas;
	}

}
