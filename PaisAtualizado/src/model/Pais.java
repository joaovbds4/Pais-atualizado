package model;

import java.io.Serializable;

public class Pais implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String populacao;
	private String area;
	
	public Pais() {}
	
	public Pais(int id, String nome, String populacao, String area) {
		setNome(nome);
		setPopulacao(populacao);
		setArea(area);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area2) {
		this.area = area2;
	}
	
	@Override
	public String toString() {
		return "{Id: " + getId() + " Nome: " + getNome() + 
				" Popula��o: " + getPopulacao() + " Area: " + getArea() + "}";
	}
}
