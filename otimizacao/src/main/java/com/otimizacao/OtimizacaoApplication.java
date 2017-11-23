package com.otimizacao;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtimizacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtimizacaoApplication.class, args);
		
		loadDLL("glpk_4_63.dll");
		loadDLL("glpk_4_63_java.dll");
	}
	
	private static void loadDLL(String local) {
		try {
			File dll = new File(local);
			System.load(dll.getAbsolutePath());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
