package br.inatel.icc.stockquotemanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

	private String url = "http://localhost:8080/";
	private RestTemplate restTemplate;
	
	public StockService () {
		restTemplate = new RestTemplate();
	}
	
	public String listAll(){
		ResponseEntity<String> response = restTemplate.getForEntity(url+"stock", String.class);
		
		return response.getBody();
	}
}
