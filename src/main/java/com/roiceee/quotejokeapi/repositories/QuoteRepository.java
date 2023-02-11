package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.JokeModel;
import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.models.QuoteModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<QuoteModel, Long> {

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
