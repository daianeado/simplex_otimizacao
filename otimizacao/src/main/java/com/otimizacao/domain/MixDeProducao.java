package com.otimizacao.domain;

import java.util.List;

public class MixDeProducao {
	
	private FuncaoObjetiva funcaoObjetiva;
	private List<Restricao> restricoes;
	
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
