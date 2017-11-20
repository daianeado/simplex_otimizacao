package com.otimizacao.domain;

public class Restricao {
	//	Variável para identificar se a restrição esta comparando se é maior igual, menor igual ou igual
	private String tipoRestricao;
	private Double x1;
	private Double x2;
	private Double result;
	
	public String getTipoRestricao() {
		return tipoRestricao;
	}
	public void setTipoRestricao(String tipoRestricao) {
		this.tipoRestricao = tipoRestricao;
	}
	public Double getX1() {
		return x1;
	}
	public void setX1(Double x1) {
		this.x1 = x1;
	}
	public Double getX2() {
		return x2;
	}
	public void setX2(Double x2) {
		this.x2 = x2;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	
}
