package com.otimizacao.domain;

public class MixDeProducao {
	
	private String metodo;	
	private Integer quantidadeVariaveisDecisao;
	private Integer quantidadeRestricao;
	private FuncaoObjetiva funcaoObjetiva;
	private Restricao restricoes;
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public Integer getQuantidadeVariaveisDecisao() {
		return quantidadeVariaveisDecisao;
	}
	public void setQuantidadeVariaveisDecisao(Integer quantidadeVariaveisDecisao) {
		this.quantidadeVariaveisDecisao = quantidadeVariaveisDecisao;
	}
	public Integer getQuantidadeRestricao() {
		return quantidadeRestricao;
	}
	public void setQuantidadeRestricao(Integer quantidadeRestricao) {
		this.quantidadeRestricao = quantidadeRestricao;
	}
	public FuncaoObjetiva getFuncaoObjetiva() {
		return funcaoObjetiva;
	}
	public void setFuncaoObjetiva(FuncaoObjetiva funcaoObjetiva) {
		this.funcaoObjetiva = funcaoObjetiva;
	}
	public Restricao getRestricoes() {
		return restricoes;
	}
	public void setRestricoes(Restricao restricoes) {
		this.restricoes = restricoes;
	}
}
