package com.otimizacao.domain;

import java.util.ArrayList;
import java.util.List;

public class FuncaoObjetiva {
	private String objetivo;
	private List<Double> x = new ArrayList<>();

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public List<Double> getX() {
		return x;
	}

	public void setX(List<Double> x) {
		this.x = x;
	}
}
