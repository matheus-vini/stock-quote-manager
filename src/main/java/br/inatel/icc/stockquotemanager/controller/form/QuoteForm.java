package br.inatel.icc.stockquotemanager.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.icc.stockquotemanager.model.Quote;
import br.inatel.icc.stockquotemanager.model.StockQuote;

public class QuoteForm {

	@NotNull @NotEmpty
	private String id;
	private Map<String, String> quotes;
	/*
	@NotNull @NotEmpty
	private LocalDate date;
	@NotNull @NotEmpty
	private BigDecimal value;
	*/

	public String getId() {
		return id;
	}

	public void setId(String stockId) {
		this.id = stockId;
	}

	public Map<String, String> getQuote() {
		return quotes;
	}

	public void setQuote(String date, String value) {
		this.quotes.put(date, value);
	}

	public StockQuote addQuote(StockQuote stockQuote) {
		Quote newQuote = null;
		
		for(Map.Entry<String, String> entry : quotes.entrySet())
		{
			LocalDate date = LocalDate.parse(entry.getKey());
			BigDecimal value = new BigDecimal(entry.getValue());
			newQuote = new Quote(date, value);
		}
		
		stockQuote.getListQuotes().add(newQuote);
		return stockQuote;
	}

}
