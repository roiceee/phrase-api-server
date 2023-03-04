package com.roiceee.quotejokeapi.repositories;

import com.roiceee.quotejokeapi.models.QuoteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            "ORDER BY RAND() LIMIT :quantity",
            nativeQuery = true)
    List<QuoteModel> getRandomQuoteList(int quantity);

    @Query(value = "SELECT * FROM quotes " +
            "WHERE PHRASE REGEXP :query " +
            "ORDER BY RAND() LIMIT :quantity",
            nativeQuery = true)
    List<QuoteModel> getRandomQuoteListWithQuery(int quantity, String query);



    Page<QuoteModel> findAllByPhraseIsContaining(String phrase, Pageable pageable);
}
