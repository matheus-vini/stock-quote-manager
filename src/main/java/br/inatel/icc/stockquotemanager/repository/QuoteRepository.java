package br.inatel.icc.stockquotemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.inatel.icc.stockquotemanager.model.StockQuote;

@Repository
public interface QuoteRepository extends JpaRepository<StockQuote, String> {

}
