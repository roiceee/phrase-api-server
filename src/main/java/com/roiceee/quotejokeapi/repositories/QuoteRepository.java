package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.JokeModel;
import com.roiceee.quotejokeapi.models.QuoteModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<QuoteModel, Long> {

    @Query(value =
            "SELECT * FROM quotes " +
                    "ORDER BY RAND() LIMIT  1",
            nativeQuery = true)
    QuoteModel getRandomQuote();

}
