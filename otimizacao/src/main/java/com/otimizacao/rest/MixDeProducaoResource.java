package com.otimizacao.rest;

import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.otimizacao.domain.MixDeProducao;
import com.otimizacao.service.MixDeProducaoService;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<MixDeProducao> getSimplex(@RequestBody MixDeProducao problema)
			throws URISyntaxException {
			MixDeProducao result = mixProducaoService.resolveSimplex(problema);
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
