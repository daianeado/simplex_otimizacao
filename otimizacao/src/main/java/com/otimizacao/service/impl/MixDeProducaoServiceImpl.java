package com.otimizacao.service.impl;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.otimizacao.domain.MixDeProducao;
import com.otimizacao.service.MixDeProducaoService;

@EnableCaching
@Service
public class MixDeProducaoServiceImpl implements MixDeProducaoService{
	
	@Override
	public MixDeProducao resolveSimplex(MixDeProducao problema) {
		//TODO resolver GLPK aqui
		
		return problema;
	}
}