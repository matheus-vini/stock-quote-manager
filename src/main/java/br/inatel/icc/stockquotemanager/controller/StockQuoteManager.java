package br.inatel.icc.stockquotemanager.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.stockquotemanager.controller.form.QuoteForm;
import br.inatel.icc.stockquotemanager.model.StockQuote;
import br.inatel.icc.stockquotemanager.repository.QuoteRepository;
import br.inatel.icc.stockquotemanager.service.StockService;

@RestController
@RequestMapping("stockquote")
public class StockQuoteManager {	
	
	@Autowired
	private QuoteRepository quoteRepository;
	@Autowired
	private StockService stockService;

	@GetMapping
	public List<StockQuote> listAll(){
		//quoteRepository.findAll();
		return stockService.listAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StockQuote> listOne(@PathVariable String id) {
		Optional<StockQuote> quote = quoteRepository.findById(id);
		if (quote.isPresent()) {
			return ResponseEntity.ok(quote.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<StockQuote> publish(@RequestBody @Valid QuoteForm form) {
		Optional<StockQuote> quote = quoteRepository.findById(form.getId());
		if (quote.isPresent()) {
			StockQuote stock = form.addQuote(quote.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(stock);
		}

		return ResponseEntity.notFound().build();
	}
}
