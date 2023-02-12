package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.QuoteModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<QuoteModel, Long> {

    @Query(value =
            "SELECT * FROM quotes " +
                    "ORDER BY RAND() LIMIT  1",
            nativeQuery = true)
    QuoteModel getRandomQuote();

    @Query(value = "SELECT * FROM quotes " +
            "ORDER BY RAND() LIMIT  :quantity",
            nativeQuery = true)
    List<QuoteModel> getRandomQuoteList(int quantity);

}
