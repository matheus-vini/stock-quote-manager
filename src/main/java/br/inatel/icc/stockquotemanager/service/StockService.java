package br.inatel.icc.stockquotemanager.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.icc.stockquotemanager.model.StockQuote;

@Service
public class StockService {

	private String url = "http://localhost:8080/";
	private RestTemplate restTemplate;
	
	public StockService () {
		restTemplate = new RestTemplate();
	}
	
	public List<StockQuote> listAll(){
		ResponseEntity<StockQuote> response = restTemplate.getForEntity(url+"stocks/", StockQuote.class);
		return (List<StockQuote>) response.getBody();
	}
}
