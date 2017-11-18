package com.otimizacao.domain;

import java.util.ArrayList;
import java.util.List;

public class Restricao {
	//	Variável para identificar se a restrição esta comparando se é maior igual, menor igual ou igual
	private List<String> tipoRestricao;
	private List<Double> x = new ArrayList<Double>();
	private List<Double> result = new ArrayList<Double>();
	
	public List<String> getTipoRestricao() {
		return tipoRestricao;
	}
	public void setTipoRestricao(List<String> tipoRestricao) {
		this.tipoRestricao = tipoRestricao;
	}
	public List<Double> getX() {
		return x;
	}
	public void setX(List<Double> x) {
		this.x = x;
	}
	public List<Double> getResult() {
		return result;
	}
	public void setResult(List<Double> result) {
		this.result = result;
	}
	
}
