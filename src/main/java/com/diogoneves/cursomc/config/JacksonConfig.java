package com.diogoneves.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.diogoneves.cursomc.domain.PagamentoComBoleto;
import com.diogoneves.cursomc.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration public class JacksonConfig {   // Código padrão (Exigência da biblioteca Jackson)
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {   
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {    
			public void configure(ObjectMapper objectMapper) {     
				objectMapper.registerSubtypes(PagamentoComCartao.class);     
				objectMapper.registerSubtypes(PagamentoComBoleto.class);     
				super.configure(objectMapper);    
			}
		};   
		return builder;  
	} 
} 
