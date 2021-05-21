package br.inatel.icc.stockquotemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class QuoteDto {

	private String stockId;
	private HashMap<LocalDate, BigDecimal> quotes = new HashMap<LocalDate, BigDecimal>();
	
	public QuoteDto() {
	}
	
	public QuoteDto(LocalDate date, BigDecimal value, String stockId) {
		this.stockId = stockId;
		this.quotes.put(date, value);
	}

	public void setQuotes(LocalDate date, BigDecimal value) {
		this.quotes.put(date, value);
	}
	
	public HashMap<LocalDate, BigDecimal> getQuotes() {
		return quotes;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
}
