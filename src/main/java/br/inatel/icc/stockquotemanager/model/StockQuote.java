package br.inatel.icc.stockquotemanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StockQuote {

	@Id
	private String stockId;
	@OneToMany
	private List<Quote> listQuotes;

	public StockQuote(String stockId) {
		this.stockId = stockId;
		this.listQuotes = null;
	}
	
	public StockQuote(String stockId, List<Quote> listQuotes) {
		this.stockId = stockId;
		this.listQuotes = listQuotes;
	}

	public String getStockId() {
		return stockId;
	}

	public List<Quote> getListQuotes() {
		return listQuotes;
	}

}
