package com.otimizacao.domain;

import java.util.List;

public class Restricao {
	//	Variável para identificar se a restrição esta comparando se é maior igual, menor igual ou igual
	private String tipoRestricao;
	private List<Double> x;
	private Double disponibilidade;
	
	public String getTipoRestricao() {
		return tipoRestricao;
	}
	public void setTipoRestricao(String tipoRestricao) {
		this.tipoRestricao = tipoRestricao;
	}
	public List<Double> getX() {
		return x;
	}
	public void setX(List<Double> x) {
		this.x = x;
	}
	public Double getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(Double disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
}