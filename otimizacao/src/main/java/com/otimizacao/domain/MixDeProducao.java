package com.otimizacao.domain;

import java.util.List;

public class MixDeProducao {
	
	private Integer quantidadeVariaveisDecisao;
	private Integer quantidadeRestricao;
	private FuncaoObjetiva funcaoObjetiva;
	private List<Restricao> restricoes;
	
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
	public List<Restricao> getRestricoes() {
		return restricoes;
	}
	public void setRestricoes(List<Restricao> restricoes) {
		this.restricoes = restricoes;
	}

}
