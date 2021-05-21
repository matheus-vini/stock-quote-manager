package br.inatel.icc.stockquotemanager.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.stockquotemanager.controller.form.QuoteForm;
import br.inatel.icc.stockquotemanager.dto.QuoteDto;
import br.inatel.icc.stockquotemanager.model.Quote;
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String listAll(){
		return stockService.listAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> listOne(@PathVariable String id) {
		String allStocks = listAll();
		if(allStocks.contains(id)) {
			Optional<StockQuote> quote = quoteRepository.findById(id);
			if(quote.isPresent()) {
				QuoteDto dto = new QuoteDto();
				dto.setStockId(quote.get().getStockId());
				List<Quote> quoteList = quote.get().getListQuotes();
				quoteList.forEach(qList -> dto.setQuotes(qList.getDate(), qList.getValue()));

				return ResponseEntity.ok(dto);
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The stock "+id+" has no quotes.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No stocks for "+id+" were found.");
	}
	
	@PostMapping("{id}")
	@Transactional
	public ResponseEntity<?> publish(@PathVariable String id, @RequestBody @Valid QuoteForm form) {
		String allStocks = listAll();
		if(allStocks.contains(id)) {
			Optional<StockQuote> quote = quoteRepository.findById(id);
			
			StockQuote stockQuote;
			if(quote.isPresent()) {
				stockQuote = quote.get();
			}
			else {
				stockQuote = new StockQuote(id);
			}
			
			stockQuote.getListQuotes().add(form.addQuote());
			quoteRepository.save(stockQuote);
			return ResponseEntity.status(HttpStatus.CREATED).body("Quote for stock "+id+" successfully registered.");
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No stocks for "+id+" were found.");
	}
}
