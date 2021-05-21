package br.inatel.icc.stockquotemanager.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.icc.stockquotemanager.model.Quote;

public class QuoteForm {

	@NotNull @NotEmpty
	private String id;
	@NotNull @NotEmpty
	private Map<String, String> quotes;

	public Map<String, String> getQuote() {
		return quotes;
	}
	
	public String getId() {
		return id;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuote(String date, String value) {
		this.quotes = Map.of(date, value);
	}

	public Quote addQuote() {
		for(Map.Entry<String, String> entry : quotes.entrySet())
		{
			System.out.println("inside EntrySet");
			LocalDate date = LocalDate.parse(entry.getKey());
			BigDecimal value = new BigDecimal(entry.getValue());
			Quote quote = new Quote(date, value, id);
			
			return quote;
		}
		
		return null;
	}

}
