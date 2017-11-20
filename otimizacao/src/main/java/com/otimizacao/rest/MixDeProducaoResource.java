package com.otimizacao.rest;

import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.otimizacao.domain.MixDeProducao;
import com.otimizacao.service.MixDeProducaoService;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class MixDeProducaoResource {
	
	@Autowired
	MixDeProducaoService mixProducaoService;
	
	@PostMapping("/otimizacao/simplex")
	@Timed
	public String getSimplex(@RequestBody MixDeProducao problema)
			throws URISyntaxException {
			String result = mixProducaoService.resolveSimplex(problema);
			return result;
	}
	
	@GetMapping("/")
	@Timed
	public String getSimplex() {
			return "Trabaho de otimização";
	}

}
